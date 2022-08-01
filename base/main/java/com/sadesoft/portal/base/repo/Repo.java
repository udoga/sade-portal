package com.sadesoft.portal.base.repo;

import java.util.List;

public interface Repo<T extends Record> {
    List<T> list();
    List<T> listPage(int pageNumber, int pageSize);
    T create(T record);
    T read(int id);
    T update(int id, T record);
    T delete(int id);
}
