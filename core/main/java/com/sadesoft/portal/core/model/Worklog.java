package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.time.LocalDate;
import java.util.Objects;

public class Worklog implements Record {
    public String id;
    public String typeId;
    public String employeeId;
    public String projectId;
    public LocalDate workDate;
    public int duration;
    public String status;
    public String description;

    public Worklog(String id, String typeId, String employeeId, String projectId, LocalDate workDate, int duration,
            String status, String description) {
        this.id = id;
        this.typeId = typeId;
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.workDate = workDate;
        this.duration = duration;
        this.status = status;
        this.description = description;
    }

    public Worklog(){}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Worklog)) return false;
        Worklog w = (Worklog) o;
        return Objects.equals(id, w.id) &&
                Objects.equals(typeId, w.typeId) &&
                Objects.equals(employeeId, w.employeeId) &&
                Objects.equals(projectId, w.projectId) &&
                Objects.equals(workDate, w.workDate) &&
                Objects.equals(duration, w.duration) &&
                Objects.equals(status, w.status) &&
                Objects.equals(description, w.description);
    }

    @Override
    public String toString() {
        return "Worklog[" +
                "id=" + id +
                " typeId=" + typeId +
                " employeeId=" + employeeId +
                " projectId=" + projectId +
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

    public Worklog withTypeId(String typeId) {
        Worklog worklog = copy();
        worklog.typeId = typeId;
        return worklog;
    }

    public Worklog withEmployeeId(String employeeId) {
        Worklog worklog = copy();
        worklog.employeeId = employeeId;
        return worklog;
    }

    public Worklog withProjectId(String projectId) {
        Worklog worklog = copy();
        worklog.projectId = projectId;
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
}
