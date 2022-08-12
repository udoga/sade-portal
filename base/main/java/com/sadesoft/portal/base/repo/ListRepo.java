package com.sadesoft.portal.base.repo;

import java.util.ArrayList;
import java.util.List;

import com.sadesoft.portal.base.error.RecordNotFoundError;

public class ListRepo<T extends Record> implements Repo<T> {
    public final String tableName;
    private final ArrayList<T> records;
    private int nextId;

    public ListRepo(String tableName) {
        this.tableName = tableName;
        this.records = new ArrayList<>();
        this.nextId = 0;
    }

    @Override
    public List<T> list() {
        return records;
    }

    @Override
    public List<T> listPage(int pageNo, int pageSize) {
        if (pageNo <= 0) throw new RuntimeException("Page number should be greater than zero");
        int fromIndex = Math.min((pageNo - 1) * pageSize, records.size());
        int toIndex = Math.min(pageNo * pageSize, records.size());
        return records.subList(fromIndex, toIndex);
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
        return records.get(index);
    }

    @Override
    public T update(String id, T record) {
        int index = findIndex(id);
        record.setId(id);
        records.set(index, record);
        return records.get(index);
    }

    @Override
    public T delete(String id) {
        int index = findIndex(id);
        return records.remove(index);
    }

    private int findIndex(String id) {
        for (int i = 0; i < records.size(); i++) if (id.equals(records.get(i).getId())) return i;
        throw new RecordNotFoundError(tableName, id);
    }
}
