package weekend.advert;

import java.util.List;

public interface AdvertDao extends AbstractDao<Advert> {

    long count();

    List<Advert> findByCategory(Category category);

    List<Advert> findByPrice(int low, int high);

    List<Advert> findByPriceAndCategory(int low, int high, Category category);

    List<Advert> findByUser(User user);
    List<Advert> findByUserLogin(String login);
}
