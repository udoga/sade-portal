package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.time.LocalDateTime;
import java.util.Objects;

public class Leave implements Record, Cloneable {
    public String id = null;
    public String personId = null;
    public LeaveType leaveType = null;
    public String replacementPersonId = null;
    public LocalDateTime startTime = null;
    public LocalDateTime endTime = null;
    public Integer dayCount = null;
    public String description = null;
    public ApprovalStatus status = null;

    public Leave() {
    }

    public Leave copy(){
        try {
            return (Leave) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Leave(String id,
                 String personId,
                 LeaveType leaveType,
                 String replacementPersonId,
                 LocalDateTime startTime,
                 LocalDateTime endTime,
                 Integer dayCount,
                 String description,
                 ApprovalStatus status) {
        this.id = id;
        this.personId = personId;
        this.leaveType = leaveType;
        this.replacementPersonId = replacementPersonId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayCount = dayCount;
        this.description = description;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Leave l)) return false;
        return Objects.equals(id, l.id) &&
                Objects.equals(personId, l.personId) &&
                Objects.equals(leaveType, l.leaveType) &&
                Objects.equals(replacementPersonId, l.replacementPersonId) &&
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
                " personId=" + personId +
                " leaveType=" + leaveType +
                " replacementPersonId=" + replacementPersonId +
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

    public Leave withPersonId(String personId) {
        Leave leave = copy();
        leave.personId = personId;
        return leave;
    }

    public Leave withLeaveType(LeaveType leaveType) {
        Leave leave = copy();
        leave.leaveType = leaveType;
        return leave;
    }

    public Leave withReplacementPersonId(String replacementPersonId) {
        Leave leave = copy();
        leave.replacementPersonId = replacementPersonId;
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
