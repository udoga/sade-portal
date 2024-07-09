package com.sadesoft.portal.core.detailed;

import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Activity;

public class DetailedWorklog extends Worklog {
    public Project project;
    public Employee employee;
    public Activity activity;

    public DetailedWorklog(Worklog w) {
        super(w);
    }

    public DetailedWorklog(Worklog w, Project p, Employee e, Activity a) {
        super(w);
        this.project = p;
        this.employee = e;
        this.activity = a;
    }

    public DetailedWorklog copy() {
        try {
            return (DetailedWorklog) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public DetailedWorklog withProject(Project project) {
        DetailedWorklog detailedWorklog = copy();
        detailedWorklog.project = project;
        return detailedWorklog;
    }

    public DetailedWorklog withEmployee(Employee employee) {
        DetailedWorklog detailedWorklog = copy();
        detailedWorklog.employee = employee;
        return detailedWorklog;
    }

    public DetailedWorklog withActivity(Activity activity) {
        DetailedWorklog detailedWorklog = copy();
        detailedWorklog.activity = activity;
        return detailedWorklog;
    }
}
