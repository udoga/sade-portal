package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.auth.Authorizer;
import com.sadesoft.portal.base.error.AuthorizationError;
import com.sadesoft.portal.core.model.Permission;
import com.sadesoft.portal.core.repo.PermissionRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
    public void authorize(String callerId, String operation, List<String> flags) {
        if (!enabled) return;
        List<Permission> permissions = (callerId == null)? List.of() : permissionRepo.listByUserId(callerId);
        for (String name: getPermissionNames(operation, flags))
            if (!contains(permissions, name))
                throw new AuthorizationError(name);
    }

    private List<String> getPermissionNames(String operation, List<String> flags) {
        return Stream.concat(Stream.of(operation), flags.stream().map(flag -> operation + "-" + flag)).toList();
    }

    private boolean contains(List<Permission> permissions, String permissionName) {
        for (Permission p: permissions) if (p.name.equals(prefix + permissionName)) return true;
        return false;
    }
}
