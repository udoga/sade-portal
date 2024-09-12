package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.auth.Authorizer;
import com.sadesoft.portal.core.detailed.DetailedWorklog;
import com.sadesoft.portal.core.query.WorklogQuery;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.repo.EmployeeRepo;
import com.sadesoft.portal.core.repo.WorklogRepo;

import java.util.List;

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
        authorizer.authorize(callerId, "worklog_list" + getSuffix(callerId, query.employeeId));
        return worklogRepo.listBy(query);
    }

    public List<DetailedWorklog> listDetailedBy(String callerId, WorklogQuery query) {
        authorizer.authorize(callerId, "worklog_list_detailed" + getSuffix(callerId, query.employeeId));
        return worklogRepo.listDetailedBy(query);
    }

    public Worklog read(String callerId, String id) {
        authorizer.authorize(callerId, "worklog_read" + getSuffix(callerId, worklogRepo.read(id).employeeId));
        return worklogRepo.read(id);
    }

    public DetailedWorklog readDetailed(String callerId, String id) {
        authorizer.authorize(callerId, "worklog_read_detailed" + getSuffix(callerId, worklogRepo.read(id).employeeId));
        return worklogRepo.readDetailed(id);
    }

    public Worklog create(String callerId, Worklog worklog) {
        authorizer.authorize(callerId, "worklog_create" + getSuffix(callerId, worklog.employeeId));
        return worklogRepo.create(worklog);
    }

    public Worklog update(String callerId, String id, Worklog worklog) {
        authorizer.authorize(callerId, "worklog_update" + getSuffix(callerId, worklogRepo.read(id).employeeId));
        return worklogRepo.update(id, worklog);
    }

    public Worklog delete(String callerId, String id) {
        authorizer.authorize(callerId, "worklog_delete" + getSuffix(callerId, worklogRepo.read(id).employeeId));
        return worklogRepo.delete(id);
    }

    private String getSuffix(String callerId, String employeeId) {
        try { return employeeRepo.read(employeeId).userId.equals(callerId)? ":own" : ""; }
        catch (Exception e) { return ""; }
    }
}
