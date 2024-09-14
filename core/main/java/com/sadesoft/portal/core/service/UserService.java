package com.sadesoft.portal.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        authorizer.authorize(callerId, "user_list", List.of());
        return userRepo.list().stream().map(s -> s.withPassword(null)).toList();
    }

    public List<User> listOptions(String callerId) {
        authorizer.authorize(callerId, "user_list_options", List.of());
        return userRepo.listOptions();
    }

    public User read(String callerId, String id) {
        authorizer.authorize(callerId, "user_read", getFlags(callerId, id));
        return userRepo.read(id).withPassword(null);
    }

    public User create(String callerId, User user) {
        authorizer.authorize(callerId, "user_create", List.of());
        check(user);
        return userRepo.create(user.withEncryptedPassword(salt)).withPassword(null);
    }

    public User update(String callerId, String id, User user) {
        authorizer.authorize(callerId, "user_update", getFlags(callerId, userRepo.read(id), user));
        check(user);
        return userRepo.update(id, user.withEncryptedPassword(salt)).withPassword(null);
    }

    public User delete(String callerId, String id) {
        authorizer.authorize(callerId, "user_delete", List.of());
        return userRepo.delete(id).withPassword(null);
    }

    private List<String> getFlags(String callerId, String id) {
        return (callerId.equals(id)? List.of() : List.of("user:other"));
    }

    private List<String> getFlags(String callerId, User oldUser, User newUser) {
        ArrayList<String> flags = new ArrayList<>(getFlags(callerId, oldUser.id));
        if (!Objects.equals(oldUser.username, newUser.username)) flags.add("username:update");
        return flags;
    }

    private void check(User user) {
        checkMissingField(user.username, "username");
        checkMissingField(user.password, "password");
    }

    private void checkMissingField(String value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }
}
