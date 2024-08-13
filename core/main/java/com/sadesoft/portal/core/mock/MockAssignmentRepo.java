package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.detailed.DetailedAssignment;
import com.sadesoft.portal.core.filter.AssignmentFilter;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.AssignmentRepo;
import com.sadesoft.portal.core.repo.RoleRepo;
import com.sadesoft.portal.core.repo.UserRepo;

import java.util.List;

public class MockAssignmentRepo extends MockRepo<Assignment> implements AssignmentRepo {
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;

    public MockAssignmentRepo(RoleRepo roleRepo, UserRepo userRepo) {
        super("Assignment");
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
    }

    @Override
    public List<Assignment> listBy(AssignmentFilter filter) {
        return records.stream().filter(e -> e.passes(filter)).toList();
    }

    @Override
    public List<DetailedAssignment> listDetailedBy(AssignmentFilter filter) {
        return listBy(filter).stream().map(a -> new DetailedAssignment(a, getUser(a.userId),
                getRole(a.roleId))).toList();
    }

    private Role getRole(String roleId) {
        return (roleRepo == null)? null : roleRepo.read(roleId);
    }

    private User getUser(String userId) {
        return (userRepo == null)? null : userRepo.read(userId);
    }
}
