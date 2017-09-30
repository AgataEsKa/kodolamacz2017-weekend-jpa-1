package weekend.task4

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

@Unroll
class UserDaoSpec extends Specification {

  EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("postgres-test")
  EntityManager entityManager = entityManagerFactory.createEntityManager()

  @Subject
  UserDao userDao = new UserDao(entityManager)

  def setup() {
    entityManager.getTransaction().begin()
    entityManager.createNativeQuery("delete from users").executeUpdate()
    entityManager.createNativeQuery("delete from towns").executeUpdate()
    entityManager.getTransaction().commit()

  }


  def "should get users from town"(String townName, List<String> expectedUsers) {
    given:
    def town = new Town("Warszawa", 100)
    def user = new User("marcin", "tajne", town)
    user.setGender(Gender.MALE)

    and:
    entityManager.getTransaction().begin()
    entityManager.persist(town)
    entityManager.persist(user)
    entityManager.getTransaction().commit()

    when:
    def actualUsers = userDao
        .getUsersFromTownJpql(townName)
        .collect({u -> u.getLogin()})

    then:
    actualUsers == expectedUsers

    where:
    townName   | expectedUsers
    "Warszawa" | ["marcin"]
    "Gdańsk"   | []
    "';drop table users;--" | []
  }
}
