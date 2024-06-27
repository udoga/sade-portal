package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.time.LocalDateTime;
import java.util.Objects;

public class Vacation implements Record {
    public String id = null;
    public String employeeId = null;
    public String categoryId = null;
    public String replacementEmployeeId = null;
    public LocalDateTime startTime = null;
    public LocalDateTime endTime = null;
    public Integer dayCount = null;
    public String description = null;
    public ApprovalStatus status = null;

    public Vacation() {
    }

    public Vacation copy() {
        try {
            return (Vacation) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Vacation(String id,
                    String employeeId,
                    String categoryId,
                    String replacementEmployeeId,
                    LocalDateTime startTime,
                    LocalDateTime endTime,
                    Integer dayCount,
                    String description,
                    ApprovalStatus status) {
        this.id = id;
        this.employeeId = employeeId;
        this.categoryId = categoryId;
        this.replacementEmployeeId = replacementEmployeeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayCount = dayCount;
        this.description = description;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vacation)) return false;
        Vacation l = (Vacation) o;
        return Objects.equals(id, l.id) &&
                Objects.equals(employeeId, l.employeeId) &&
                Objects.equals(categoryId, l.categoryId) &&
                Objects.equals(replacementEmployeeId, l.replacementEmployeeId) &&
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
                " replacementEmployeeId=" + replacementEmployeeId +
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

    public Vacation withReplacementEmployeeId(String replacementEmployeeId) {
        Vacation vacation = copy();
        vacation.replacementEmployeeId = replacementEmployeeId;
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

    public Vacation withStatus(ApprovalStatus status) {
        Vacation vacation = copy();
        vacation.status = status;
        return vacation;
    }
}
