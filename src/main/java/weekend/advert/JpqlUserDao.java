package weekend.advert;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpqlUserDao extends JpqlAbstractDao<User>
    implements UserDao{

    private static final Logger LOG = Logger.getLogger(JpqlUserDao.class);


    public JpqlUserDao(EntityManager entityManager) {
        super(entityManager, User.class);
        LOG.info("Stworzono DAO");
    }

    @Override
    public Optional<User> findByLogin(String login) {
        LOG.info("Wyszukujemy po loginie="+login);
        List<User> list = entityManager.createQuery(
                "select u from User u where u.login = :login", User.class)
                .setParameter("login", login)
                .getResultList();
//        if(list.size() == 1){
//            return Optional.ofNullable(list.get(0));
//        }else{
//            return Optional.empty();
//        }
        // nie do końca to samo, bo powyższy kod zwróci empty
        // jeśli będą dwa i więcej elementów,
        // a ten w takim wypadku zawsze coś zwróci
        return list.stream().findFirst();
    }
}
