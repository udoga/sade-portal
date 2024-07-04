package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.repo.RoleRepo;

import java.util.List;

public class RoleService {
    private final RoleRepo repo;

    public RoleService(RoleRepo repo) {
        this.repo = repo;
    }

    public List<Role> list() {
        return repo.list();
    }

    public Role read(String id) {
        return repo.read(id);
    }

    public Role create(Role role) {
        return repo.create(role);
    }

    public Role update(String id, Role role) {
        return repo.update(id, role);
    }

    public Role delete(String id) {
        return repo.delete(id);
    }
}
