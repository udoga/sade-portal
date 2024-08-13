package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.detailed.DetailedAssignment;
import com.sadesoft.portal.core.filter.AssignmentFilter;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.repo.AssignmentRepo;

import java.util.List;

public class AssignmentService {
    private final AssignmentRepo repo;

    public AssignmentService(AssignmentRepo repo) {
        this.repo = repo;
    }

    public List<Assignment> listBy(AssignmentFilter filter) {
        return repo.listBy(filter);
    }

    public List<DetailedAssignment> listDetailedBy(AssignmentFilter filter) {
        return repo.listDetailedBy(filter);
    }

    public Assignment create(Assignment assignment) {
        return repo.create(assignment);
    }

    public Assignment delete(String id) {
        return repo.delete(id);
    }
}
