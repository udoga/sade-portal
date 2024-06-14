package com.sadesoft.portal.core.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.repo.RoleRepo;

public class MockRoleRepo extends MockRepo<Role> implements RoleRepo {
    private final HashMap<String, List<Role>> userRoles = new HashMap<>();

    public MockRoleRepo() {
        super("Role");
    }

    @Override
    public List<Role> listByUserId(String userId) {
        return userRoles.getOrDefault(userId, new ArrayList<>());
    }

    public void addUserRole(String userId, Role role) {
        if (!userRoles.containsKey(userId)) userRoles.put(userId, new ArrayList<>());
        userRoles.get(userId).add(role);
    }
}
