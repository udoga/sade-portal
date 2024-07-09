package com.sadesoft.portal.base.repo;

import com.sadesoft.portal.base.error.RecordNotFoundError;

import java.util.ArrayList;
import java.util.List;

public class MockRepo<T extends Record> implements Repo<T> {
    public final String tableName;
    protected final ArrayList<T> records;
    protected int nextId;

    public MockRepo(String tableName) {
        this.tableName = tableName;
        this.records = new ArrayList<>();
        this.nextId = 0;
    }

    @Override
    public List<T> list() {
        ArrayList<T> list = new ArrayList<T>();
        for (T t: records) list.add((T) t.copy());
        return list;
    }

    @Override
    public T create(T record) {
        record.setId(String.valueOf(nextId++));
        records.add(record);
        return record;
    }

    @Override
    public T read(String id) {
        int index = findIndex(id);
        return list().get(index);
    }

    @Override
    public T update(String id, T record) {
        int index = findIndex(id);
        record.setId(id);
        records.set(index, record);
        return list().get(index);
    }

    @Override
    public T delete(String id) {
        int index = findIndex(id);
        return records.remove(index);
    }

    private int findIndex(String id) {
        for (int i = 0; i < records.size(); i++) if (id.equals(records.get(i).getId())) return i;
        throw new RecordNotFoundError(tableName, "id=" + id);
    }
}
