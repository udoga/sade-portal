package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.detailed.DetailedAssignment;
import com.sadesoft.portal.core.query.AssignmentQuery;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.repo.AssignmentRepo;

import java.util.List;

public class AssignmentService {
    private final AssignmentRepo repo;

    public AssignmentService(AssignmentRepo repo) {
        this.repo = repo;
    }

    public List<Assignment> listBy(AssignmentQuery query) {
        return repo.listBy(query);
    }

    public List<DetailedAssignment> listDetailedBy(AssignmentQuery query) {
        return repo.listDetailedBy(query);
    }

    public Assignment create(Assignment assignment) {
        return repo.create(assignment);
    }

    public Assignment delete(String id) {
        return repo.delete(id);
    }
}
