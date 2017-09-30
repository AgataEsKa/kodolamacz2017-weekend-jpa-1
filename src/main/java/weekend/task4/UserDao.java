package weekend.task4;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDao {
  private final EntityManager entityManager;

  public UserDao(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  //
  public List<User> getUsersFromTown(String town) {
    //noinspection unchecked
    return entityManager.createNativeQuery(
            "SELECT * FROM users u JOIN towns t ON u.town_id = t.id " +
                    "WHERE t.name = '" + town + "'",
            User.class
    )
//            .setParameter("town", town)
            .getResultList();
  }

  public List<User> getUsersFromTownJpql(String town) {
    return entityManager
            .createQuery("select u from User u " +
                    "where u.town.name = :town", User.class)
            .setParameter("town", town)
            .getResultList();
  }
}