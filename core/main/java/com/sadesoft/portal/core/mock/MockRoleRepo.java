package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.repo.RoleRepo;

public class MockRoleRepo extends MockRepo<Role> implements RoleRepo {
    public MockRoleRepo() {
        super("Role");
    }
}
