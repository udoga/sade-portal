package uds.samplepanel.core;

import uds.samplepanel.base.Table;

import java.util.List;

public class UserService {
    private final Table<User> table;

    public UserService(Table<User> table) {
        this.table = table;
    }

    public List<User> list() {
        return table.list();
    }

    public User read(int id) {
        return table.read(id);
    }

    public User create(User user) {
        return table.create(user);
    }

    public User update(int id, User user) {
        return table.update(id, user);
    }

    public User delete(int id) {
        return table.delete(id);
    }
}
