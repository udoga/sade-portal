package com.sadesoft.portal.core.service;

import java.util.List;

import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.User;

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
        validateFields(user);
        return repo.create(user);
    }

    public User update(int id, User user) {
        validateFields(user);
        return repo.update(id, user);
    }

    public User delete(int id) {
        return repo.delete(id);
    }

    private void validateFields(User user) {
        validateExists(user.username, "username");
        validateExists(user.fullName, "fullName");
    }

    private void validateExists(String fieldValue, String fieldName) {
        if (fieldValue == null || fieldValue.isEmpty()) throw new MissingFieldError("User", fieldName);
    }
}
