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
    public List<Assignment> listBy(String userId) {
        return records.stream().filter(e -> e.matches(userId)).toList();
    }

    @Override
    public List<DetailedAssignment> listDetailedBy(String userId) {
        return listBy(userId).stream().map(a -> new DetailedAssignment(a).withRole(getRole(a.roleId))).toList();
    }

    private Role getRole(String roleId) {
        return (roleRepo == null)? null : roleRepo.read(roleId);
    }
}
