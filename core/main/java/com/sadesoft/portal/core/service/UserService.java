package com.sadesoft.portal.core.service;

import java.util.List;
import java.util.stream.Collectors;

import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.UserRepo;

public class UserService {
    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public List<User> list() {
        return repo.list().stream().map(s -> s.withPassword(null)).collect(Collectors.toList());
    }

    public User read(String id) {
        return repo.read(id).withPassword(null);
    }

    public User create(User user) {
        check(user);
        return repo.create(user).withPassword(null);
    }

    public User update(String id, User user) {
        check(user);
        return repo.update(id, user).withPassword(null);
    }

    public User delete(String id) {
        return repo.delete(id).withPassword(null);
    }

    private void check(User user) {
        checkMissingField(user.username, "username");
        checkMissingField(user.password, "password");
    }

    private void checkMissingField(String value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }
}
