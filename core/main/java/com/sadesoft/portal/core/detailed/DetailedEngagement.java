package com.sadesoft.portal.core.detailed;

import com.sadesoft.portal.core.model.Engagement;
import com.sadesoft.portal.core.model.Position;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Employee;

public class DetailedEngagement extends Engagement {
    public Project project;
    public Employee employee;
    public Position position;

    public DetailedEngagement(Engagement e) {
        super(e);
    }

    public DetailedEngagement(Engagement e, Project p, Employee m, Position o) {
        super(e);
        this.project = p;
        this.employee = m;
        this.position = o;
    }

    public DetailedEngagement copy() {
        try {
            return (DetailedEngagement) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public DetailedEngagement withProject(Project project) {
        DetailedEngagement detailedEngagement = copy();
        detailedEngagement.project = project;
        return detailedEngagement;
    }

    public DetailedEngagement withEmployee(Employee employee) {
        DetailedEngagement detailedEngagement = copy();
        detailedEngagement.employee = employee;
        return detailedEngagement;
    }

    public DetailedEngagement withPosition(Position position) {
        DetailedEngagement detailedEngagement = copy();
        detailedEngagement.position = position;
        return detailedEngagement;
    }
}
