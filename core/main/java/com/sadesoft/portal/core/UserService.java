package com.sadesoft.portal.core;

import com.sadesoft.portal.base.Repo;

import java.util.List;

public class UserService {
    private final Repo<User> repo;

    public UserService(Repo<User> repo) {
        this.repo = repo;
    }

    public List<User> list() {
        return repo.list();
    }

    public User read(int id) {
        return repo.read(id);
    }

    public User create(User user) {
        return repo.create(user);
    }

    public User update(int id, User user) {
        return repo.update(id, user);
    }

    public User delete(int id) {
        return repo.delete(id);
    }
}
