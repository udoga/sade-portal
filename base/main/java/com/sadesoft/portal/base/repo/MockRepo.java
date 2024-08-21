package com.sadesoft.portal.base.repo;

import com.sadesoft.portal.base.error.InvalidIdError;
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
        ArrayList<T> list = new ArrayList<>();
        for (T t: records) list.add(copy(t));
        return list;
    }

    @Override
    public T create(T record) {
        if (record.getId() != null) throw new InvalidIdError(record.getId());
        T copy = copy(record);
        copy.setId(String.valueOf(nextId++));
        records.add(copy);
        return copy;
    }

    @Override
    public T read(String id) {
        return copy(records.get(findIndex(id)));
    }

    @Override
    public T update(String id, T record) {
        if (record.getId() != null && !record.getId().equals(id)) throw new InvalidIdError(record.getId());
        T copy = copy(record);
        copy.setId(id);
        records.set(findIndex(id), copy);
        return copy;
    }

    @Override
    public T delete(String id) {
        return records.remove(findIndex(id));
    }

    protected List<T> applyOffset(List<T> list, Integer offset) {
        return (offset == null)? list : list.subList(offset, list.size());
    }

    @SuppressWarnings("unchecked")
    private T copy(T record) {
        return (T) record.copy();
    }

    private int findIndex(String id) {
        for (int i = 0; i < records.size(); i++) if (id.equals(records.get(i).getId())) return i;
        throw new RecordNotFoundError(tableName, "id=" + id);
    }
}
