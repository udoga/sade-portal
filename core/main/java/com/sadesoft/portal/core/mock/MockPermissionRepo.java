package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Permission;
import com.sadesoft.portal.core.repo.PermissionRepo;

import java.util.Comparator;
import java.util.List;

public class MockPermissionRepo extends MockRepo<Permission> implements PermissionRepo {
    public String authorizedUserId;

    public MockPermissionRepo() {
        super("Permission");
    }

    @Override
    public List<Permission> listOptions() {
        return list().stream().sorted(getComparator()).toList();
    }

    @Override
    public List<Permission> listByUserId(String userId) {
        return (userId.equals(authorizedUserId))? list() : List.of();
    }

    private static Comparator<Permission> getComparator() {
        return Comparator.comparing((Permission p) -> p.name);
    }
}
