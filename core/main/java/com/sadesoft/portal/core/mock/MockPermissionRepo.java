package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Permission;
import com.sadesoft.portal.core.repo.PermissionRepo;

import java.util.Comparator;
import java.util.List;

public class MockPermissionRepo extends MockRepo<Permission> implements PermissionRepo {
    private List<String> authorizedCallerIds;

    public MockPermissionRepo() {
        super("Permission");
        this.authorizedCallerIds = List.of();
    }

    public void authorize(List<String> callerIds) {
        this.authorizedCallerIds = callerIds;
    }

    public void create(List<String> requestNames) {
        for (String name: requestNames) create(new Permission(null, "sp-" + name));
    }

    @Override
    public List<Permission> listOptions() {
        return list().stream().sorted(getComparator()).toList();
    }

    @Override
    public List<Permission> listByUserId(String userId) {
        return authorizedCallerIds.contains(userId)? list() : List.of();
    }

    private static Comparator<Permission> getComparator() {
        return Comparator.comparing((Permission p) -> p.name);
    }
}
