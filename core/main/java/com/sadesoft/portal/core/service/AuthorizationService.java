package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.auth.Authorizer;
import com.sadesoft.portal.base.error.AuthorizationError;
import com.sadesoft.portal.core.model.Permission;
import com.sadesoft.portal.core.repo.PermissionRepo;

import java.util.List;

public class AuthorizationService implements Authorizer {
    private final boolean enabled;
    private final PermissionRepo permissionRepo;

    public AuthorizationService(boolean enabled, PermissionRepo permissionRepo) {
        this.enabled = enabled;
        this.permissionRepo = permissionRepo;
    }

    @Override
    public void authorize(String callerId, String requestName) {
        if (enabled && (callerId == null || !contains(permissionRepo.listByUserId(callerId), requestName)))
            throw new AuthorizationError(requestName);
    }

    private boolean contains(List<Permission> permissions, String requestName) {
        for (Permission p: permissions) if (("sp-" + requestName).startsWith(p.name)) return true;
        return false;
    }
}
