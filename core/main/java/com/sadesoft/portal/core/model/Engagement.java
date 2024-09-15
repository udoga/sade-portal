package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;
import com.sadesoft.portal.core.query.EngagementQuery;

import java.time.LocalDate;
import java.util.Objects;

public class Engagement implements Record {
    public String id;
    public String projectId;
    public String employeeId;
    public String positionId;
    public LocalDate startDate;
    public LocalDate endDate;
    public String description;
    public String status;

    public Engagement() {
    }

    public Engagement(String id, String projectId, String employeeId, String positionId, LocalDate startDate,
            LocalDate endDate, String description, String status) {
        this.id = id;
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.positionId = positionId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.status = status;
    }

    public Engagement(Engagement r) {
        this.id = r.id;
        this.projectId = r.projectId;
        this.employeeId = r.employeeId;
        this.positionId = r.positionId;
        this.startDate = r.startDate;
        this.endDate = r.endDate;
        this.description = r.description;
        this.status = r.status;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Engagement)) return false;
        Engagement r = (Engagement) o;
        return Objects.equals(id, r.id) &&
                Objects.equals(projectId, r.projectId) &&
                Objects.equals(employeeId, r.employeeId) &&
                Objects.equals(positionId, r.positionId) &&
                Objects.equals(startDate, r.startDate) &&
                Objects.equals(endDate, r.endDate) &&
                Objects.equals(description, r.description) &&
                Objects.equals(status, r.status);
    }

    @Override
    public String toString() {
        return "Engagement[" +
                "id=" + id +
                " projectId=" + projectId +
                " employeeId=" + employeeId +
                " positionId=" + positionId +
                " startDate=" + startDate +
                " endDate=" + endDate +
                " description=" + description +
                " status=" + status +
                "]";
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Engagement copy() {
        try {
            return (Engagement) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Engagement withId(String id) {
        Engagement copy = copy();
        copy.id = id;
        return copy;
    }

    public Engagement withProjectId(String projectId) {
        Engagement copy = copy();
        copy.projectId = projectId;
        return copy;
    }

    public Engagement withEmployeeId(String employeeId) {
        Engagement copy = copy();
        copy.employeeId = employeeId;
        return copy;
    }

    public Engagement withPositionId(String positionId) {
        Engagement copy = copy();
        copy.positionId = positionId;
        return copy;
    }

    public Engagement withStartDate(LocalDate startDate) {
        Engagement copy = copy();
        copy.startDate = startDate;
        return copy;
    }

    public Engagement withEndDate(LocalDate endDate) {
        Engagement copy = copy();
        copy.endDate = endDate;
        return copy;
    }

    public Engagement withDescription(String description) {
        Engagement copy = copy();
        copy.description = description;
        return copy;
    }

    public Engagement withStatus(String status) {
        Engagement copy = copy();
        copy.status = status;
        return copy;
    }

    public boolean matches(EngagementQuery q) {
        if (q.projectId != null && !q.projectId.equals(projectId)) return false;
        if (q.employeeId != null && !q.employeeId.equals(employeeId)) return false;
        return true;
    }
}
