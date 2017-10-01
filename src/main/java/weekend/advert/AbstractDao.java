package weekend.advert;

public interface AbstractDao<T extends AbstractEntity> {

    void save(T t);

    T findById(int id);

}
