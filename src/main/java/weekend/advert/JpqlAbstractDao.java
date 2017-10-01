package weekend.advert;

import javax.persistence.EntityManager;

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
    public T findById(int id){
        return entityManager.find(tClass, id);
    }

}
