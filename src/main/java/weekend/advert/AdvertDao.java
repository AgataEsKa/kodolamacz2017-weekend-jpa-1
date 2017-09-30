package weekend.advert;

import javax.persistence.EntityManager;

public class AdvertDao {

    private EntityManager entityManager;

    public AdvertDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    void save(Advert advert){
        entityManager.getTransaction().begin();
        entityManager.persist(advert);
        entityManager.getTransaction().commit();
    }

    public long count() {
        return entityManager.createQuery("select count(a.id) from Advert a", Long.class)
                .getSingleResult();
    }

    Advert findById(int id){
        return entityManager.find(Advert.class, id);
    }
}
