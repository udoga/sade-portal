package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.model.Permission;
import com.sadesoft.portal.core.repo.PermissionRepo;

import java.util.List;

public class PermissionService {
    private final PermissionRepo repo;

    public PermissionService(PermissionRepo repo) {
        this.repo = repo;
    }

    public List<Permission> list() {
        return repo.list();
    }

    public List<Permission> listSummarized() {
        return repo.listSummarized();
    }

    public Permission read(String id) {
        return repo.read(id);
    }
}
