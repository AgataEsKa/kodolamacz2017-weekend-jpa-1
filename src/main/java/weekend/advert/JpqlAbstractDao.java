package weekend.advert;

import javax.persistence.EntityManager;
import java.util.List;

abstract class JpqlAbstractDao<T extends AbstractEntity>
        implements AbstractDao<T> {

    protected EntityManager entityManager;
    private Class<T> tClass;

    protected JpqlAbstractDao(EntityManager entityManager, Class<T> tClass) {
        this.entityManager = entityManager;
        this.tClass = tClass;
    }

    @Override
    public void save(T entity){
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public T findById(int id){
        return entityManager.find(tClass, id);
    }

    @Override
    public List<T> findAll() {
        // działa, ale jest brzydkie bo jeśli anotacją
        // zmienimy nazwę encji (@Entity) to ta metoda się wywali :(
        return entityManager.createQuery(
                "select t from "+tClass.getName()+" t", tClass)
                .getResultList();
    }
}
