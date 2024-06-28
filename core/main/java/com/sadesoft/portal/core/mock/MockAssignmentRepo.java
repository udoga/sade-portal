package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.error.RecordNotFoundError;
import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.compound.AssignmentWithRole;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.repo.AssignmentRepo;
import com.sadesoft.portal.core.repo.RoleRepo;

import java.util.List;
import java.util.stream.Collectors;

public class MockAssignmentRepo extends MockRepo<Assignment> implements AssignmentRepo {
    private final RoleRepo roleRepo;

    public MockAssignmentRepo(RoleRepo roleRepo) {
        super("Assignment");
        this.roleRepo = roleRepo;
    }

    @Override
    public Assignment deleteByValues(Assignment a) {
        return delete(readByValues(a).id);
    }

    private Assignment readByValues(Assignment a) {
        for (Assignment r: records) if (r.withId(null).equals(a.withId(null))) return r;
        throw new RecordNotFoundError(tableName, "record=" + a);
    }

    @Override
    public List<AssignmentWithRole> listWithRole() {
        return records.stream().map(a -> new AssignmentWithRole(a, getRole(a.roleId))).collect(Collectors.toList());
    }

    private Role getRole(String roleId) {
        return (roleRepo == null)? null : roleRepo.read(roleId);
    }
}
