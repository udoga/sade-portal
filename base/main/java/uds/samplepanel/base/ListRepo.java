package uds.samplepanel.base;

import java.util.ArrayList;
import java.util.List;

public class ListRepo<T extends Record> implements Repo<T> {
    public final String typeName;
    private final ArrayList<T> records;
    private int nextId;

    public ListRepo(String typeName) {
        this.typeName = typeName;
        this.records = new ArrayList<>();
        this.nextId = 0;
    }

    @Override
    public List<T> list() {
        return records;
    }

    @Override
    public T create(T record) {
        record.id = nextId++;
        records.add(record);
        return record;
    }

    @Override
    public T read(int id) {
        int index = findIndex(id);
        return records.get(index);
    }

    @Override
    public T update(int id, T record) {
        int index = findIndex(id);
        record.id = id;
        records.set(index, record);
        return records.get(index);
    }

    @Override
    public T delete(int id) {
        int index = findIndex(id);
        return records.remove(index);
    }

    private int findIndex(int id) {
        for (int i = 0; i < records.size(); i++) if (records.get(i).id == id) return i;
        throw new RecordNotFound(typeName, id);
    }
}
