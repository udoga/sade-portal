package com.sadesoft.portal.core.sample;

import com.sadesoft.portal.core.model.ApprovalStatus;
import com.sadesoft.portal.core.model.Leave;
import com.sadesoft.portal.core.model.LeaveType;

import java.time.LocalDateTime;

public class LeaveSamples {
    public static Leave validLeave = new Leave(
            null,
            "4",
            LeaveType.AnnualLeave,
            "6",
            LocalDateTime.of(2022,5,4,9,18),
            LocalDateTime.of(2022,5,4,9,19),
            5,
            "descriptionExample",
            null);
}
