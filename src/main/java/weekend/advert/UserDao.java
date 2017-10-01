package weekend.advert;

public interface UserDao extends AbstractDao<User> {

    User findByLogin(String login);

}
