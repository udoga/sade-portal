package com.sadesoft.portal.core.service;

import java.util.List;

import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.base.auth.Authorizer;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.UserRepo;

public class UserService {
    private final UserRepo userRepo;
    private final Authorizer authorizer;
    private final String salt;

    public UserService(UserRepo userRepo, Authorizer authorizer, String salt) {
        this.userRepo = userRepo;
        this.authorizer = authorizer;
        this.salt = salt;
    }

    public List<User> list(String callerId) {
        authorizer.authorize(callerId, "user_list");
        return userRepo.list().stream().map(s -> s.withPassword(null)).toList();
    }

    public List<User> listOptions(String callerId) {
        authorizer.authorize(callerId, "user_list_options");
        return userRepo.listOptions();
    }

    public User read(String callerId, String id) {
        authorizer.authorize(callerId, "user_read" + (callerId.equals(id)? "" : "-user:other"));
        return userRepo.read(id).withPassword(null);
    }

    public User create(String callerId, User user) {
        authorizer.authorize(callerId, "user_create");
        check(user);
        return userRepo.create(user.withEncryptedPassword(salt)).withPassword(null);
    }

    public User update(String callerId, String id, User user) {
        authorizer.authorize(callerId, "user_update");
        check(user);
        return userRepo.update(id, user.withEncryptedPassword(salt)).withPassword(null);
    }

    public User delete(String callerId, String id) {
        authorizer.authorize(callerId, "user_delete");
        return userRepo.delete(id).withPassword(null);
    }

    private void check(User user) {
        checkMissingField(user.username, "username");
        checkMissingField(user.password, "password");
    }

    private void checkMissingField(String value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }
}
