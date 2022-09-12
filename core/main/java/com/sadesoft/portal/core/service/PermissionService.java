package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.model.Permission;
import com.sadesoft.portal.core.repo.PermissionRepo;

import java.util.List;

public class PermissionService {
    private PermissionRepo repo;

    public PermissionService(PermissionRepo repo) {
        this.repo = repo;
    }

    public Permission read(String id) {
        return repo.read(id);
    }

    public List<Permission> list() {
        return repo.list();
    }
}
