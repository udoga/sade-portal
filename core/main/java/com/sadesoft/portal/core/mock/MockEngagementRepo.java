package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.base.repo.MockRepo;
import com.sadesoft.portal.core.detailed.DetailedEngagement;
import com.sadesoft.portal.core.filter.EngagementFilter;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Position;
import com.sadesoft.portal.core.model.Engagement;
import com.sadesoft.portal.core.repo.EmployeeRepo;
import com.sadesoft.portal.core.repo.EngagementRepo;
import com.sadesoft.portal.core.repo.ProjectRepo;
import com.sadesoft.portal.core.repo.PositionRepo;

import java.util.List;

public class MockEngagementRepo extends MockRepo<Engagement> implements EngagementRepo {
    private final ProjectRepo projectRepo;
    private final EmployeeRepo employeeRepo;
    private final PositionRepo positionRepo;

    public MockEngagementRepo(ProjectRepo projectRepo, EmployeeRepo employeeRepo, PositionRepo positionRepo) {
        super("Engagement");
        this.projectRepo = projectRepo;
        this.employeeRepo = employeeRepo;
        this.positionRepo = positionRepo;
    }

    @Override
    public List<Engagement> listBy(EngagementFilter filter) {
        return records.stream().filter(e -> e.passes(filter)).toList();
    }

    @Override
    public List<DetailedEngagement> listDetailedBy(EngagementFilter filter) {
        return listBy(filter).stream().map(e -> new DetailedEngagement(e, getProject(e.projectId),
                getEmployee(e.employeeId), getPosition(e.positionId))).toList();
    }

    private Project getProject(String projectId) {
        return (projectRepo == null)? null : projectRepo.read(projectId);
    }

    private Employee getEmployee(String employeeId) {
        return (employeeRepo == null)? null : employeeRepo.read(employeeId);
    }

    private Position getPosition(String positionId) {
        return (positionRepo == null)? null : positionRepo.read(positionId);
    }
}