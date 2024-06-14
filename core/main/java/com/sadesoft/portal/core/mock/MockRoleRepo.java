package com.sadesoft.portal.core.mock;

import java.util.ArrayList;
import java.util.List;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.repo.RoleRepo;

public class MockRoleRepo extends MockRepo<Role> implements RoleRepo {
    private List<Role> userRoles = new ArrayList<Role>();

    public MockRoleRepo() {
        super("Role");
    }

    @Override
    public List<Role> listByUserId(String userId) {
        return userRoles;
    }

    public void addUserRole(Role role) {
        userRoles.add(role);
    }
}
