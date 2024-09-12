package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.repo.RoleRepo;

import java.util.List;
import java.util.regex.Pattern;

public class RoleService {
    private final RoleRepo repo;

    public RoleService(RoleRepo repo) {
        this.repo = repo;
    }

    public List<Role> list() {
        return repo.list();
    }

    public List<Role> listOptions() {
        return repo.listOptions();
    }

    public Role read(String id) {
        return repo.read(id);
    }

    public Role create(Role role) {
        check(role);
        return repo.create(role);
    }

    public Role update(String id, Role role) {
        check(role);
        return repo.update(id, role);
    }

    public Role delete(String id) {
        return repo.delete(id);
    }

    private void check(Role role) {
        checkMissingField(role.name, "name");
        checkFieldFormat(role.name, "name", "^[a-zA-Z]+( [a-zA-Z]+)*$");
    }

    private void checkMissingField(String value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkFieldFormat(String value, String field, String regex) {
        if (!Pattern.matches(regex, value)) throw new InvalidInputError("Format of field '" + field + "' is invalid.");
    }
}
