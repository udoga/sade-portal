package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.auth.Authorizer;
import com.sadesoft.portal.base.error.InconsistencyError;
import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.detailed.DetailedWorklog;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.query.EmployeeQuery;
import com.sadesoft.portal.core.query.EngagementQuery;
import com.sadesoft.portal.core.query.WorklogQuery;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.repo.EmployeeRepo;
import com.sadesoft.portal.core.repo.EngagementRepo;
import com.sadesoft.portal.core.repo.WorklogRepo;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;
import java.util.regex.Pattern;

public class WorklogService {
    private final WorklogRepo worklogRepo;
    private final EmployeeRepo employeeRepo;
    private final EngagementRepo engagementRepo;
    private final Authorizer authorizer;

    public WorklogService(WorklogRepo worklogRepo, EmployeeRepo employeeRepo, EngagementRepo engagementRepo,
                          Authorizer authorizer) {
        this.worklogRepo = worklogRepo;
        this.employeeRepo = employeeRepo;
        this.engagementRepo = engagementRepo;
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
        authorizer.authorize(callerId, "worklog_create", getFlags(callerId, worklog));
        check(worklog);
        return worklogRepo.create(worklog);
    }

    public Worklog update(String callerId, String id, Worklog worklog) {
        authorizer.authorize(callerId, "worklog_update", getFlags(callerId, worklogRepo.read(id), worklog));
        check(worklog);
        return worklogRepo.update(id, worklog);
    }

    public Worklog delete(String callerId, String id) {
        authorizer.authorize(callerId, "worklog_delete", getFlags(callerId, worklogRepo.read(id)));
        return worklogRepo.delete(id);
    }

    private List<String> getFlags(String callerId, String employeeId) {
        return isEmployeeOther(callerId, employeeId)? List.of("employee:other") : List.of();
    }

    private List<String> getFlags(String callerId, Worklog worklog) {
        ArrayList<String> flags = new ArrayList<>(getFlags(callerId, worklog.employeeId));
        if (isApprovalResponded(worklog)) flags.add("approval:responded");
        if (isProjectUnengaged(getEmployeeId(callerId), worklog.projectId)) flags.add("project:unengaged");
        return flags;
    }

    private List<String> getFlags(String callerId, Worklog oldWorklog, Worklog newWorklog) {
        ArrayList<String> flags = new ArrayList<>(getFlags(callerId, oldWorklog));
        if (!Objects.equals(oldWorklog.employeeId, newWorklog.employeeId)) flags.add("employee_change");
        if (!Objects.equals(oldWorklog.approvalStatus, newWorklog.approvalStatus)) flags.add("approval_change");
        if (!Objects.equals(oldWorklog.projectId, newWorklog.projectId)) flags.add("project_change");
        return flags;
    }

    private String getEmployeeId(String callerId) {
        List<Employee> employees = employeeRepo.listBy(new EmployeeQuery().withUserId(callerId));
        return (callerId == null || employees.isEmpty())? null : employees.get(0).id;
    }

    private boolean isEmployeeOther(String callerId, String employeeId) {
        return (callerId == null || employeeId == null || !employeeId.equals(getEmployeeId(callerId)));
    }

    private boolean isApprovalResponded(Worklog w) {
        return (w.approvalStatus != null);
    }

    private boolean isProjectUnengaged(String employeeId, String projectId) {
        if (employeeId == null || projectId == null) return true;
        EngagementQuery query = new EngagementQuery().withEmployeeId(employeeId).withProjectId(projectId);
        return engagementRepo.listBy(query).isEmpty();
    }

    private void check(Worklog worklog) {
        checkMissingFields(worklog);
        checkFieldFormats(worklog);
        if (isProjectUnengaged(worklog.employeeId, worklog.projectId))
            throw new InconsistencyError("Employee is not engaged in the selected project.");
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
        if (worklog.approvalStatus != null) checkFieldFormat(worklog.approvalStatus, "approvalStatus", "^[AD]$");
    }

    private void checkMissingField(Object value, String field) {
        if (value == null) throw new MissingFieldError(field);
    }

    private void checkFieldFormat(String value, String field, String regex) {
        if (!Pattern.matches(regex, value)) throw new InvalidInputError("Format of field '" + field + "' is invalid.");
    }
}
