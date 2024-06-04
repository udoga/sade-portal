package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.time.LocalDateTime;
import java.util.Objects;

public class Leave implements Record {
    public String id = null;
    public String employeeId = null;
    public LeaveType leaveType = null;
    public String replacementEmployeeId = null;
    public LocalDateTime startTime = null;
    public LocalDateTime endTime = null;
    public Integer dayCount = null;
    public String description = null;
    public ApprovalStatus status = null;

    public Leave() {
    }

    public Leave copy() {
        try {
            return (Leave) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Leave(String id,
                 String employeeId,
                 LeaveType leaveType,
                 String replacementEmployeeId,
                 LocalDateTime startTime,
                 LocalDateTime endTime,
                 Integer dayCount,
                 String description,
                 ApprovalStatus status) {
        this.id = id;
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.replacementEmployeeId = replacementEmployeeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayCount = dayCount;
        this.description = description;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Leave)) return false;
        Leave l = (Leave) o;
        return Objects.equals(id, l.id) &&
                Objects.equals(employeeId, l.employeeId) &&
                Objects.equals(leaveType, l.leaveType) &&
                Objects.equals(replacementEmployeeId, l.replacementEmployeeId) &&
                Objects.equals(startTime,l.startTime) &&
                Objects.equals(endTime, l.endTime) &&
                Objects.equals(dayCount, l.dayCount) &&
                Objects.equals(description, l.description) &&
                Objects.equals(status, l.status);
    }

    @Override
    public String toString() {
        return "Leave[" +
                "id=" + id +
                " employeeId=" + employeeId +
                " leaveType=" + leaveType +
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

    public Leave withId(String id) {
        Leave leave = copy();
        leave.id = id;
        return leave;
    }

    public Leave withEmployeeId(String employeeId) {
        Leave leave = copy();
        leave.employeeId = employeeId;
        return leave;
    }

    public Leave withLeaveType(LeaveType leaveType) {
        Leave leave = copy();
        leave.leaveType = leaveType;
        return leave;
    }

    public Leave withReplacementEmployeeId(String replacementEmployeeId) {
        Leave leave = copy();
        leave.replacementEmployeeId = replacementEmployeeId;
        return leave;
    }

    public Leave withStartTime(LocalDateTime startTime) {
        Leave leave = copy();
        leave.startTime = startTime;
        return leave;
    }

    public Leave withEndTime(LocalDateTime endTime) {
        Leave leave = copy();
        leave.endTime = endTime;
        return leave;
    }

    public Leave withDayCount(Integer dayCount) {
        Leave leave = copy();
        leave.dayCount = dayCount;
        return leave;
    }

    public Leave withDescription(String description) {
        Leave leave = copy();
        leave.description = description;
        return leave;
    }

    public Leave withStatus(ApprovalStatus status) {
        Leave leave = copy();
        leave.status = status;
        return leave;
    }
}
