package weekend.advert;

import org.hamcrest.core.IsCollectionContaining;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class AdvertDaoTest {

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("postgres-test");
    static EntityManager entityManager = entityManagerFactory.createEntityManager();

    static User owner = new User("owner","pass");
    static AdvertDao advertDao = new JpqlAdvertDao(entityManager);
    static UserDao userDao = new JpqlUserDao(entityManager);

    @BeforeClass
    public static void init(){
        userDao.save(owner);
        Advert advert = new Advert("Sprzedam Astre","Nówka sztuka",5000, Category.CAR);
        advert.setOwner(owner);
        advertDao.save(advert);
    }

    @Test
    public void shouldSaveAdvertTest(){
        long before = advertDao.count();
        // given
        Advert advert = new Advert("Sprzedam Opla","Nówka sztuka",5000, Category.CAR);
        advert.setOwner(owner);
        // when
        advertDao.save(advert);

        // then
        long count = advertDao.count();

        assertEquals(before + 1, count);
    }

    @Test
    public void shouldFindByUserTest(){
        // given

        // when
        List<Advert> adverts = advertDao.findByUser(owner);
        // then
        assertThat(adverts, hasSize(greaterThanOrEqualTo(1)));

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
        advertDao.save(new Advert("","",100,Category.CAR));
        advertDao.save(new Advert("","",5,Category.BICYCLE));

        // when
        int count = advertDao.findByCategory(Category.CAR).size();

        // then
        assertEquals(before + 1, count);

    }

    @Test
    public void shouldFindByPriceTest(){
        // given
        Advert car = new Advert("Toyota", "sprzedam", 100, Category.CAR);
        advertDao.save(car);
        advertDao.save(new Advert("","",200,Category.BICYCLE));

        // when
        List<Advert> adverts = advertDao.findByPrice(50, 150);

        // then
//        boolean containsCar = false;
//        for (Advert advert : adverts) {
//            Assert.assertTrue(advert.getPrice() >= 50);
//            Assert.assertTrue(advert.getPrice() <= 150);
//            if(advert.getId() == car.getId()){
//                System.out.println("Znalazłem");
//                System.out.println(advert);
//                containsCar = true;
//            }
//        }
//        Assert.assertTrue(containsCar);

        //ta metoda z biblioteki hamcrest zastępuje mi powyższy
        // wykomentowany kod
        for (Advert advert : adverts) {
            assertThat(advert.getPrice(), is(greaterThanOrEqualTo(50)));
            assertThat(advert.getPrice(), is(lessThanOrEqualTo(150)));
        }
        // sprawdza czy dana lista zawiera konkretny obiekt
        assertThat(adverts, hasItem(car));

    }

    @Test
    public void shouldFindByPriceAndCategoryTest(){
        // given
        Advert car = new Advert("Toyota", "sprzedam", 100, Category.CAR);
        advertDao.save(car);
        advertDao.save(new Advert("","",200,Category.BICYCLE));
        advertDao.save(new Advert("","",175,Category.CAR));
        advertDao.save(new Advert("","",75,Category.BICYCLE));

        // when
        List<Advert> adverts = advertDao.findByPriceAndCategory(50, 150, Category.CAR);

        // then
        for (Advert advert : adverts) {
            assertThat(advert.getPrice(), is(greaterThanOrEqualTo(50)));
            assertThat(advert.getPrice(), is(lessThanOrEqualTo(150)));
            assertThat(advert.getCategory(), is(equalTo(Category.CAR)));
        }
        // sprawdza czy dana lista zawiera konkretny obiekt
        assertThat(adverts, hasItem(car));

    }
}
