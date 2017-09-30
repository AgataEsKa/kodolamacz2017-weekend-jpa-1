package weekend.advert;

import java.util.List;

public interface AdvertDao {

    void save(Advert advert);

    Advert findById(int id);

    long count();

    List<Advert> findByCategory(Category category);
}
