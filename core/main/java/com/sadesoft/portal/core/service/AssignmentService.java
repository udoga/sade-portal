package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.detailed.DetailedAssignment;
import com.sadesoft.portal.core.query.AssignmentQuery;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.repo.AssignmentRepo;

import java.util.List;
import java.util.regex.Pattern;

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

    public Assignment read(String id) {
        return repo.read(id);
    }

    public Assignment create(Assignment assignment) {
        check(assignment);
        return repo.create(assignment);
    }

    public Assignment update(String id, Assignment assignment) {
        return repo.update(id, assignment);
    }

    public Assignment delete(String id) {
        return repo.delete(id);
    }

    private void check(Assignment assignment) {
        checkMissingFields(assignment);
        checkFieldFormats(assignment);
    }

    private void checkMissingFields(Assignment assignment) {
        checkMissingField(assignment.userId, "userId");
        checkMissingField(assignment.roleId, "roleId");
    }

    private void checkFieldFormats(Assignment assignment) {
        checkFieldFormat(assignment.userId, "userId", "^[0-9]+$");
        checkFieldFormat(assignment.roleId, "roleId", "^[0-9]+$");
    }

    private void checkMissingField(String value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkFieldFormat(String value, String field, String regex) {
        if (!Pattern.matches(regex, value)) throw new InvalidInputError("Format of field '" + field + "' is invalid.");
    }
}
