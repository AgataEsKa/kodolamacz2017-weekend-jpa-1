package weekend.advert;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.List;

// jeśli ustawimy parametr name w anotacji Entity
// to zmienimy nazwę ENCJI (co pociągnie za sobą zmianę
// nazwy tabeli również), ale co ważniejsze -
// od teraz wszystkie zapytania JPQL muszą podawać nową nazwę encji:
// select u from users u
// zamiast wcześniejszego
// select u from User u
// jeśli chcemy wyłącznie zmienić nazwę tabeli to używamy do tego
// anotacji Table (w przypadku gdy nazwa klasy jest zastrzezonym
// słowem w naszej bazie danych)
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;
    private String phone;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    List<Advert> adverts;

    @ManyToMany
    List<User> friends;

    public User() {
        this("","","");
    }

    public User(String login, String password) {
        this(login, password, "brak numeru");
    }

    public User(String login, String password, String phone) {
        this.login = login;
        this.password = password;
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", friends=" + friends +
                "} " + super.toString();
    }
}
