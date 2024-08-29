package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;
import com.sadesoft.portal.core.query.VacationQuery;

import java.time.LocalDateTime;
import java.util.Objects;

public class Vacation implements Record {
    public String id;
    public String employeeId;
    public String categoryId;
    public LocalDateTime startTime;
    public LocalDateTime endTime;
    public Integer dayCount;
    public String description;
    public String status;

    public Vacation copy() {
        try {
            return (Vacation) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Vacation() {
    }

    public Vacation(String id,
                    String employeeId,
                    String categoryId,
                    LocalDateTime startTime,
                    LocalDateTime endTime,
                    Integer dayCount,
                    String description,
                    String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.categoryId = categoryId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayCount = dayCount;
        this.description = description;
        this.status = status;
    }

    public Vacation(Vacation v) {
        this.id = v.id;
        this.employeeId = v.employeeId;
        this.categoryId = v.categoryId;
        this.startTime = v.startTime;
        this.endTime = v.endTime;
        this.dayCount = v.dayCount;
        this.description = v.description;
        this.status = v.status;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vacation)) return false;
        Vacation l = (Vacation) o;
        return Objects.equals(id, l.id) &&
                Objects.equals(employeeId, l.employeeId) &&
                Objects.equals(categoryId, l.categoryId) &&
                Objects.equals(startTime,l.startTime) &&
                Objects.equals(endTime, l.endTime) &&
                Objects.equals(dayCount, l.dayCount) &&
                Objects.equals(description, l.description) &&
                Objects.equals(status, l.status);
    }

    @Override
    public String toString() {
        return "Vacation[" +
                "id=" + id +
                " employeeId=" + employeeId +
                " categoryId=" + categoryId +
                " startTime=" + startTime +
                " endTime=" + endTime +
                " dayCount=" + description +
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

    public Vacation withId(String id) {
        Vacation vacation = copy();
        vacation.id = id;
        return vacation;
    }

    public Vacation withEmployeeId(String employeeId) {
        Vacation vacation = copy();
        vacation.employeeId = employeeId;
        return vacation;
    }

    public Vacation withVacationCategoryId(String categoryId) {
        Vacation vacation = copy();
        vacation.categoryId = categoryId;
        return vacation;
    }

    public Vacation withStartTime(LocalDateTime startTime) {
        Vacation vacation = copy();
        vacation.startTime = startTime;
        return vacation;
    }

    public Vacation withEndTime(LocalDateTime endTime) {
        Vacation vacation = copy();
        vacation.endTime = endTime;
        return vacation;
    }

    public Vacation withDayCount(Integer dayCount) {
        Vacation vacation = copy();
        vacation.dayCount = dayCount;
        return vacation;
    }

    public Vacation withDescription(String description) {
        Vacation vacation = copy();
        vacation.description = description;
        return vacation;
    }

    public Vacation withStatus(String status) {
        Vacation vacation = copy();
        vacation.status = status;
        return vacation;
    }

    public boolean matches(VacationQuery q) {
        if (q.status != null && !status.equals(q.status)) return false;
        if (q.employeeId != null && !employeeId.equals(q.employeeId)) return false;
        return true;
    }
}
