package com.sadesoft.portal.core.model;

import com.sadesoft.portal.core.sample.LeaveSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LeaveTest {
    private Leave validLeave;

    @BeforeEach
    public void setup() {
        validLeave = LeaveSamples.validLeave.copy();
    }

    @Test
    public void initializesWithoutFields() {
        Leave l = new Leave();
        assertNull(l.id);
        assertNull(l.userId);
        assertNull(l.leaveType);
        assertNull(l.replacementUserId);
        assertNull(l.startTime);
        assertNull(l.endTime);
        assertNull(l.dayCount);
        assertNull(l.description);
        assertNull(l.status);
    }

    @Test
    public void printsFields() {
        assertEquals("Leave[id=null" +
                " userId=4" +
                " leaveType=AnnualLeave" +
                " replacementUserId=6" +
                " startTime=2022-05-04T09:18" +
                " endTime=2022-05-04T09:19" +
                " dayCount=descriptionExample" +
                " description=descriptionExample" +
                " status=null]", validLeave.toString());
    }

    @Test
    public void clonesWithFields() {
        assertNull(validLeave.withId(null).id);
        assertNull(validLeave.withUserId(null).userId);
        assertNull(validLeave.withLeaveType(null).leaveType);
        assertNull(validLeave.withReplacementUserId(null).replacementUserId);
        assertNull(validLeave.withStartTime(null).startTime);
        assertNull(validLeave.withEndTime(null).endTime);
        assertNull(validLeave.withDayCount(null).dayCount);
        assertNull(validLeave.withDescription(null).description);
        assertNull(validLeave.withStatus(null).status);
    }

    @Test
    public void checksEquality() {
        assertEquals(validLeave, validLeave.copy());
        assertNotEquals(validLeave, validLeave.withId("0"));
        assertNotEquals(validLeave, validLeave.withUserId(null));
        assertNotEquals(validLeave, validLeave.withLeaveType(null));
        assertNotEquals(validLeave, validLeave.withReplacementUserId(null));
        assertNotEquals(validLeave, validLeave.withStartTime(null));
        assertNotEquals(validLeave, validLeave.withEndTime(null));
        assertNotEquals(validLeave, validLeave.withDayCount(null));
        assertNotEquals(validLeave, validLeave.withDescription(null));
        assertNotEquals(validLeave, validLeave.withStatus(ApprovalStatus.Approved));
    }
}
