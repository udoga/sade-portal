package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.auth.Authorizer;
import com.sadesoft.portal.base.error.AuthorizationError;
import com.sadesoft.portal.core.model.Permission;
import com.sadesoft.portal.core.repo.PermissionRepo;

import java.util.List;

public class AuthorizationService implements Authorizer {
    private final PermissionRepo permissionRepo;
    private final boolean enabled;
    private final String prefix;

    public AuthorizationService(PermissionRepo permissionRepo, boolean enabled, String prefix) {
        this.permissionRepo = permissionRepo;
        this.enabled = enabled;
        this.prefix = prefix;
    }

    @Override
    public void authorize(String callerId, String permissionName) {
        if (enabled && (callerId == null || !contains(permissionRepo.listByUserId(callerId), permissionName)))
            throw new AuthorizationError(permissionName);
    }

    private boolean contains(List<Permission> permissions, String permissionName) {
        for (Permission p: permissions) if (p.name.equals(prefix + permissionName)) return true;
        return false;
    }
}
