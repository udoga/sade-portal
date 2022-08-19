package com.sadesoft.portal.core.model;


import com.sadesoft.portal.base.repo.Record;

import java.time.LocalDateTime;

public class Leave implements Record, Cloneable{
    public String id = null;
    public LeaveType leaveType = null;
    public String replacementPersonId = null;
    public LocalDateTime startTime = null;
    public LocalDateTime endTime = null;
    public Integer dayCount = null;
    public String description = null;
    public ApprovalStatus status = null;

    public Leave() {
    }

    public Leave(String id,
                 LeaveType leaveType,
                 String replacementPersonId,
                 LocalDateTime startTime,
                 LocalDateTime endTime,
                 Integer dayCount,
                 String description,
                 ApprovalStatus status) {
        this.id = id;
        this.leaveType = leaveType;
        this.replacementPersonId = replacementPersonId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayCount = dayCount;
        this.description = description;
        this.status = status;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
