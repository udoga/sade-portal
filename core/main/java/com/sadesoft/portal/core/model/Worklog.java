package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.time.LocalDate;
import java.util.Objects;

public class Worklog implements Record {
    public String id;
    public String projectId;
    public String employeeId;
    public String activityId;
    public LocalDate workDate;
    public Integer duration;
    public String status;
    public String description;

    public Worklog(){}

    public Worklog(String id, String projectId, String employeeId, String activityId, LocalDate workDate,
            Integer duration, String status, String description) {
        this.id = id;
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.activityId = activityId;
        this.workDate = workDate;
        this.duration = duration;
        this.status = status;
        this.description = description;
    }

    public Worklog(Worklog w) {
        this.id = w.id;
        this.projectId = w.projectId;
        this.employeeId = w.employeeId;
        this.activityId = w.activityId;
        this.workDate = w.workDate;
        this.duration = w.duration;
        this.status = w.status;
        this.description = w.description;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Worklog)) return false;
        Worklog w = (Worklog) o;
        return Objects.equals(id, w.id) &&
                Objects.equals(projectId, w.projectId) &&
                Objects.equals(employeeId, w.employeeId) &&
                Objects.equals(activityId, w.activityId) &&
                Objects.equals(workDate, w.workDate) &&
                Objects.equals(duration, w.duration) &&
                Objects.equals(status, w.status) &&
                Objects.equals(description, w.description);
    }

    @Override
    public String toString() {
        return "Worklog[" +
                "id=" + id +
                " projectId=" + projectId +
                " employeeId=" + employeeId +
                " activityId=" + activityId +
                " workDate=" + workDate +
                " duration=" + duration +
                " status=" + status +
                " description=" + description +
                ']';
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Worklog copy() {
        try {
            return (Worklog) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Worklog withId(String id) {
        Worklog worklog = copy();
        worklog.id = id;
        return worklog;
    }

    public Worklog withProjectId(String projectId) {
        Worklog worklog = copy();
        worklog.projectId = projectId;
        return worklog;
    }

    public Worklog withEmployeeId(String employeeId) {
        Worklog worklog = copy();
        worklog.employeeId = employeeId;
        return worklog;
    }

    public Worklog withActivityId(String activityId) {
        Worklog worklog = copy();
        worklog.activityId = activityId;
        return worklog;
    }

    public Worklog withWorkDate(LocalDate workDate) {
        Worklog worklog = copy();
        worklog.workDate = workDate;
        return worklog;
    }

    public Worklog withDuration(int duration) {
        Worklog worklog = copy();
        worklog.duration = duration;
        return worklog;
    }

    public Worklog withStatus(String status) {
        Worklog worklog = copy();
        worklog.status = status;
        return worklog;
    }

    public Worklog withDescription(String description) {
        Worklog worklog = copy();
        worklog.description = description;
        return worklog;
    }

    public boolean matches(String afterId) {
        return afterId == null || id.compareTo(afterId) > 0;
    }
}
