package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.compound.AssignmentWithRole;
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

    public Assignment deleteByValues(Assignment a) {
        return repo.deleteByValues(a);
    }

    public List<AssignmentWithRole> listWithRole() {
        return repo.listWithRole();
    }
}
