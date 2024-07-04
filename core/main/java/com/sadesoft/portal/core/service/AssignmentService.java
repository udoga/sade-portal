package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.detailed.DetailedAssignment;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.repo.AssignmentRepo;

import java.util.List;

public class AssignmentService {
    private final AssignmentRepo repo;

    public AssignmentService(AssignmentRepo repo) {
        this.repo = repo;
    }

    public List<Assignment> list() {
        return repo.list();
    }

    public Assignment create(Assignment assignment) {
        return repo.create(assignment);
    }

    public Assignment delete(String id) {
        return repo.delete(id);
    }

    public List<DetailedAssignment> listWithRoleByUserId(String userId) {
        return (userId == null)? repo.listWithRole() : repo.listWithRoleByUserId(userId);
    }
}
