package uds.samplepanel.base;

import java.util.List;

public interface Repo<T extends Record> {
    List<T> list();
    T create(T record);
    T read(int id);
    T update(int id, T record);
    T delete(int id);
}
