package com.sadesoft.portal.base.repo;

import java.util.List;

public interface Repo<T extends Record> {
    List<T> list();
    T create(T record);
    T read(String id);
    T update(String id, T record);
    T delete(String id);
}
