package weekend.advert;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserDaoTest {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("postgres-test");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    UserDao userDao = new JpqlUserDao(entityManager);

    @Test
    public void shouldFindByLoginTest(){
        // given
        String login = "bartek";
        User user = new User(login, "pass123");
        userDao.save(user);
        // when
        Optional<User> byLogin = userDao.findByLogin(login);
//        User byLogin = userDao.findByLogin(login).orElseThrow(AssertionError::new);

        // then
        assertThat(byLogin.isPresent(), is(true));
        assertThat(byLogin.get().getLogin(), is(equalTo(login)));
    }

    @Test
    public void shouldNotFindByLoginTest(){
        // given

        // when
        Optional<User> byLogin = userDao.findByLogin("notExists");

        // then
        assertThat(byLogin.isPresent(), is(false));

    }

}
