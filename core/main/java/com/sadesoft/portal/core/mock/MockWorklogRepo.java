package com.sadesoft.portal.core.mock;

import java.util.List;

import com.sadesoft.portal.core.detailed.DetailedWorklog;
import com.sadesoft.portal.core.model.Activity;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.repo.EmployeeRepo;
import com.sadesoft.portal.core.repo.ActivityRepo;
import com.sadesoft.portal.core.repo.ProjectRepo;
import com.sadesoft.portal.core.repo.WorklogRepo;

public class MockWorklogRepo extends MockRepo<Worklog> implements WorklogRepo {
    private final ProjectRepo projectRepo;
    private final EmployeeRepo employeeRepo;
    private final ActivityRepo activityRepo;

    public MockWorklogRepo(ProjectRepo projectRepo, EmployeeRepo employeeRepo, ActivityRepo activityRepo) {
        super("Worklog");
        this.projectRepo = projectRepo;
        this.employeeRepo = employeeRepo;
        this.activityRepo = activityRepo;
    }

    @Override
    public List<Worklog> listBy(String afterId) {
        return records.stream().filter(w -> w.matches(afterId)).toList();
    }

    @Override
    public List<DetailedWorklog> listDetailedBy(String afterId) {
        return listBy(afterId).stream().map(w -> new DetailedWorklog(w, getProject(w.projectId),
                getEmployee(w.employeeId), getActivity(w.activityId))).toList();
    }

    @Override
    public DetailedWorklog readDetailed(String id) {
        Worklog worklog = read(id);
        return new DetailedWorklog(worklog, getProject(worklog.projectId), getEmployee(worklog.employeeId),
                getActivity(worklog.activityId));
    }

    private Project getProject(String projectId) {
        return (projectRepo == null)? null : projectRepo.read(projectId);
    }

    private Employee getEmployee(String employeeId) {
        return (employeeRepo == null)? null : employeeRepo.read(employeeId);
    }

    private Activity getActivity(String activityId) {
        return (activityRepo == null)? null : activityRepo.read(activityId);
    }
}
