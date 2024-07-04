package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.detailed.DetailedAssignment;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.repo.AssignmentRepo;
import com.sadesoft.portal.core.repo.RoleRepo;

import java.util.List;

public class MockAssignmentRepo extends MockRepo<Assignment> implements AssignmentRepo {
    private final RoleRepo roleRepo;

    public MockAssignmentRepo(RoleRepo roleRepo) {
        super("Assignment");
        this.roleRepo = roleRepo;
    }

    @Override
    public List<DetailedAssignment> listWithRole() {
        return records.stream().map(a -> new DetailedAssignment(a).withRole(getRole(a.roleId))).toList();
    }

    @Override
    public List<DetailedAssignment> listWithRoleByUserId(String userId) {
        return listWithRole().stream().filter(a -> a.userId.equals(userId)).toList();
    }

    private Role getRole(String roleId) {
        return (roleRepo == null)? null : roleRepo.read(roleId);
    }
}
