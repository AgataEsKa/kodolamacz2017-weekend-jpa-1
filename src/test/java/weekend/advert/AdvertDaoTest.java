package weekend.advert;

import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class AdvertDaoTest {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("postgres-test");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    AdvertDao advertDao = new JpqlAdvertDao(entityManager);

    @Test
    public void shouldSaveAdvertTest(){
        long before = advertDao.count();
        // given
        Advert advert = new Advert("Sprzedam Opla","Nówka sztuka", Category.CAR);

        // when
        advertDao.save(advert);

        // then
        long count = advertDao.count();

        Assert.assertEquals(before + 1, count);
    }

    @Test
    public void shouldTest(){
        // given
        Advert advert = advertDao.findById(5);
        System.out.println(advert);
        // when

        // then

    }

    @Test
    public void shouldFindByCategoryTest(){
        // given
        int before = advertDao.findByCategory(Category.CAR).size();
        advertDao.save(new Advert("","",Category.CAR));
        advertDao.save(new Advert("","",Category.BICYCLE));

        // when
        int count = advertDao.findByCategory(Category.CAR).size();

        // then
        Assert.assertEquals(before + 1, count);

    }
}
