package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.repo.RoleRepo;

import java.util.List;

public class RoleService {
    private RoleRepo repo;

    public RoleService(RoleRepo repo) {
        this.repo = repo;
    }

    public Role read(String id) {
        return repo.read(id);
    }

    public List<Role> list() {
        return repo.list();
    }
}
