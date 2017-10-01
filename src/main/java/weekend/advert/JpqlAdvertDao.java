package weekend.advert;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

public class JpqlAdvertDao extends JpqlAbstractDao<Advert>
        implements AdvertDao {


    public JpqlAdvertDao(EntityManager entityManager) {
        super(entityManager, Advert.class);
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(a.id) from Advert a", Long.class)
                .getSingleResult();
    }

    @Override
    public List<Advert> findByCategory(Category category) {
        return entityManager.createQuery(
                "select a from Advert a where a.category = :category", Advert.class)
                .setParameter("category", category)
                .getResultList();
    }

    @Override
    public List<Advert> findByPrice(int low, int high) {
        return entityManager.createQuery(
                "select a from Advert a where a.price >= ?1 and a.price <= ?2", Advert.class)
                .setParameter(1, low)
                .setParameter(2, high)
                .getResultList();
//        return Collections.emptyList();
    }

    @Override
    public List<Advert> findByPriceAndCategory(int low, int high, Category category) {
        return entityManager.createQuery(
                "select a from Advert a " +
                        "where a.price >= ?1 and a.price <= ?2 " +
                        "and a.category = :category", Advert.class)
                .setParameter(1, low)
                .setParameter(2, high)
                .setParameter("category", category)
                .getResultList();
    }

}
