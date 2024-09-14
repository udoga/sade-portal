package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.auth.Authorizer;
import com.sadesoft.portal.base.error.ClientError;
import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.detailed.DetailedWorklog;
import com.sadesoft.portal.core.query.WorklogQuery;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.repo.EmployeeRepo;
import com.sadesoft.portal.core.repo.WorklogRepo;

import java.util.List;

import java.util.regex.Pattern;

public class WorklogService {
    private final WorklogRepo worklogRepo;
    private final EmployeeRepo employeeRepo;
    private final Authorizer authorizer;

    public WorklogService(WorklogRepo worklogRepo, EmployeeRepo employeeRepo, Authorizer authorizer) {
        this.worklogRepo = worklogRepo;
        this.employeeRepo = employeeRepo;
        this.authorizer = authorizer;
    }

    public List<Worklog> listBy(String callerId, WorklogQuery query) {
        authorizer.authorize(callerId, "worklog_list", getFlags(callerId, query.employeeId));
        return worklogRepo.listBy(query);
    }

    public List<DetailedWorklog> listDetailedBy(String callerId, WorklogQuery query) {
        authorizer.authorize(callerId, "worklog_list_detailed", getFlags(callerId, query.employeeId));
        return worklogRepo.listDetailedBy(query);
    }

    public Worklog read(String callerId, String id) {
        authorizer.authorize(callerId, "worklog_read", getFlags(callerId, worklogRepo.read(id).employeeId));
        return worklogRepo.read(id);
    }

    public DetailedWorklog readDetailed(String callerId, String id) {
        authorizer.authorize(callerId, "worklog_read_detailed", getFlags(callerId, worklogRepo.read(id).employeeId));
        return worklogRepo.readDetailed(id);
    }

    public Worklog create(String callerId, Worklog worklog) {
        authorizer.authorize(callerId, "worklog_create", getFlags(callerId, worklog.employeeId));
        check(worklog);
        checkForbiddenField(worklog.approvalStatus, "approvalStatus");
        return worklogRepo.create(worklog);
    }

    public Worklog update(String callerId, String id, Worklog worklog) {
        authorizer.authorize(callerId, "worklog_update", getFlags(callerId, worklogRepo.read(id).employeeId));
        check(worklog);
        if (worklog.approvalStatus != null) checkFieldFormat(worklog.approvalStatus, "approvalStatus", "^[AD]$");
        return worklogRepo.update(id, worklog);
    }

    public Worklog delete(String callerId, String id) {
        authorizer.authorize(callerId, "worklog_delete", getFlags(callerId, worklogRepo.read(id).employeeId));
        return worklogRepo.delete(id);
    }

    private List<String> getFlags(String callerId, String employeeId) {
        try { return employeeRepo.read(employeeId).userId.equals(callerId)? List.of() : List.of("employee:other"); }
        catch (Exception e) { return List.of(); }
    }

    private void check(Worklog worklog) {
        checkMissingFields(worklog);
        checkFieldFormats(worklog);
    }

    private void checkMissingFields(Worklog worklog) {
        checkMissingField(worklog.projectId, "projectId");
        checkMissingField(worklog.employeeId, "employeeId");
        checkMissingField(worklog.activityId, "activityId");
        checkMissingField(worklog.workDate, "workDate");
        checkMissingField(worklog.duration, "duration");
        checkMissingField(worklog.description, "description");
    }

    private void checkFieldFormats(Worklog worklog) {
        checkFieldFormat(worklog.projectId, "projectId", "^[0-9]+$");
        checkFieldFormat(worklog.employeeId, "employeeId", "^[0-9]+$");
        checkFieldFormat(worklog.activityId, "activityId", "^[0-9]+$");
    }

    private void checkMissingField(Object value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkForbiddenField(Object value, String field) {
        if (value != null) throw new ClientError("Field '" + field + "' should not exist.");
    }

    private void checkFieldFormat(String value, String field, String regex) {
        if (!Pattern.matches(regex, value)) throw new InvalidInputError("Format of field '" + field + "' is invalid.");
    }
}
