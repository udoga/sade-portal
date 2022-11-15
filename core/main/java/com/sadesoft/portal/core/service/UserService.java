package com.sadesoft.portal.core.service;

import java.util.List;

import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.UserRepo;

public class UserService {
    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public List<User> list() {
        return repo.list();
    }

    public List<User> listPage(int pageNo, int pageSize) {
        return repo.listPage(pageNo, pageSize);
    }

    public User read(String id) {
        return repo.read(id);
    }

    public User create(User user) {
        check(user);
        return repo.create(user);
    }

    public User update(String id, User user) {
        check(user);
        return repo.update(id, user);
    }

    public User delete(String id) {
        return repo.delete(id);
    }

    private void check(User user) {
        checkMissingField(user.username, "username");
        checkMissingField(user.password, "password");
    }

    private void checkMissingField(String value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }
}
