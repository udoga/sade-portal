package com.sadesoft.portal.core.mock;

import java.util.List;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.repo.RoleRepo;

public class MockRoleRepo extends MockRepo<Role> implements RoleRepo {
    public MockRoleRepo() {
        super("Role");
    }

    @Override
    public List<Role> listOptions() {
        return list().stream().map(s -> new Role().withId(s.id).withName(s.name)).toList();
    }
}
