package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.model.Permission;
import com.sadesoft.portal.core.repo.PermissionRepo;

public class MockPermissionRepo extends MockRepo<Permission> implements PermissionRepo {
    public MockPermissionRepo() {
        super("Permission");
    }
}
