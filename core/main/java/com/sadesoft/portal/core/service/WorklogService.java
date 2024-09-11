package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.AuthorizationError;
import com.sadesoft.portal.core.detailed.DetailedWorklog;
import com.sadesoft.portal.core.model.Permission;
import com.sadesoft.portal.core.query.WorklogQuery;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.repo.EmployeeRepo;
import com.sadesoft.portal.core.repo.PermissionRepo;
import com.sadesoft.portal.core.repo.WorklogRepo;

import java.util.List;

public class WorklogService {
    private final WorklogRepo worklogRepo;
    private final PermissionRepo permissionRepo;
    private final EmployeeRepo employeeRepo;

    public WorklogService(WorklogRepo worklogRepo, PermissionRepo permissionRepo, EmployeeRepo employeeRepo) {
        this.worklogRepo = worklogRepo;
        this.permissionRepo = permissionRepo;
        this.employeeRepo = employeeRepo;
    }

    public List<Worklog> listBy(String callerId, WorklogQuery query) {
        authorize(callerId, "worklog_list" + getSuffix(callerId, query.employeeId));
        return worklogRepo.listBy(query);
    }

    public List<DetailedWorklog> listDetailedBy(String callerId, WorklogQuery query) {
        authorize(callerId, "worklog_list_detailed" + getSuffix(callerId, query.employeeId));
        return worklogRepo.listDetailedBy(query);
    }

    public Worklog read(String callerId, String id) {
        authorize(callerId, "worklog_read" + getSuffix(callerId, worklogRepo.read(id).employeeId));
        return worklogRepo.read(id);
    }

    public DetailedWorklog readDetailed(String callerId, String id) {
        authorize(callerId, "worklog_read_detailed" + getSuffix(callerId, worklogRepo.read(id).employeeId));
        return worklogRepo.readDetailed(id);
    }

    public Worklog create(String callerId, Worklog worklog) {
        authorize(callerId, "worklog_create" + getSuffix(callerId, worklog.employeeId));
        return worklogRepo.create(worklog);
    }

    public Worklog update(String callerId, String id, Worklog worklog) {
        authorize(callerId, "worklog_update" + getSuffix(callerId, worklogRepo.read(id).employeeId));
        return worklogRepo.update(id, worklog);
    }

    public Worklog delete(String callerId, String id) {
        authorize(callerId, "worklog_delete" + getSuffix(callerId, worklogRepo.read(id).employeeId));
        return worklogRepo.delete(id);
    }

    private String getSuffix(String callerId, String employeeId) {
        try { return employeeRepo.read(employeeId).userId.equals(callerId)? ":own" : ""; }
        catch (Exception e) { return ""; }
    }

    private void authorize(String callerId, String requestName) {
        List<Permission> permissions = permissionRepo.listByUserId(callerId);
        for (Permission p: permissions) if (("sp-" + requestName).startsWith(p.name)) return;
        throw new AuthorizationError(requestName);
    }
}
