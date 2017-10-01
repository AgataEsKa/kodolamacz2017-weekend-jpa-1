package weekend.advert;

import java.util.Optional;

public interface UserDao extends AbstractDao<User> {

    Optional<User> findByLogin(String login);

}
