package com.sadesoft.portal.core.mock;

import java.util.List;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.filter.GrantFilter;
import com.sadesoft.portal.core.detailed.DetailedGrant;
import com.sadesoft.portal.core.model.Grant;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.model.Permission;
import com.sadesoft.portal.core.repo.GrantRepo;
import com.sadesoft.portal.core.repo.PermissionRepo;
import com.sadesoft.portal.core.repo.RoleRepo;

public class MockGrantRepo extends MockRepo<Grant> implements GrantRepo {
    private final RoleRepo roleRepo;
    private final PermissionRepo permissionRepo;

    public MockGrantRepo(RoleRepo roleRepo, PermissionRepo permissionRepo) {
        super("Grant");
        this.roleRepo = roleRepo;
        this.permissionRepo = permissionRepo;
    }

    @Override
    public List<Grant> listBy(GrantFilter f) {
        return applyOffset(records.stream().filter(e -> e.passes(f)).toList(), f.offset);
    }

    @Override
    public List<DetailedGrant> listDetailedBy(GrantFilter filter) {
        return listBy(filter).stream().map(g -> new DetailedGrant(g, getRole(g.roleId),
                getPermission(g.permissionId))).toList();
    }

    private Role getRole(String roleId) {
        return (roleRepo == null)? null : roleRepo.read(roleId);
    }

    private Permission getPermission(String permissionId) {
        return (permissionRepo == null)? null : permissionRepo.read(permissionId);
    }
}
