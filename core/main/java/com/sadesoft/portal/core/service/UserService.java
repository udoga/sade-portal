package com.sadesoft.portal.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.RoleRepo;
import com.sadesoft.portal.core.repo.UserRepo;

public class UserService {
    private final UserRepo repo;
    private final RoleRepo roleRepo;
    private final String salt;

    public UserService(UserRepo repo, RoleRepo roleRepo, String salt) {
        this.repo = repo;
        this.roleRepo = roleRepo;
        this.salt = salt;
    }

    public List<User> list() {
        return repo.list().stream().map(s -> s.withPassword(null)).collect(Collectors.toList());
    }

    public User read(String id) {
        return repo.read(id).withPassword(null);
    }

    public User create(User user) {
        check(user);
        return repo.create(user.withEncryptedPassword(salt)).withPassword(null);
    }

    public User update(String id, User user) {
        check(user);
        return repo.update(id, user.withEncryptedPassword(salt)).withPassword(null);
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

    public List<Role> listRolesByUserId(String userId) {
        return roleRepo.listByUserId(userId);
    }
}
