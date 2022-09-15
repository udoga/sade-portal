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
        assertNull(l.personId);
        assertNull(l.leaveType);
        assertNull(l.replacementPersonId);
        assertNull(l.startTime);
        assertNull(l.endTime);
        assertNull(l.dayCount);
        assertNull(l.description);
        assertNull(l.status);
    }

    @Test
    public void printsFields() {
        assertEquals("Leave[id=null" +
                " personId=4" +
                " leaveType=AnnualLeave" +
                " replacementPersonId=6" +
                " startTime=2022-05-04T09:18" +
                " endTime=2022-05-04T09:19" +
                " dayCount=descriptionExample" +
                " description=descriptionExample" +
                " status=null]", validLeave.toString());
    }

    @Test
    public void clonesWithFields() {
        assertNull(validLeave.withId(null).id);
        assertNull(validLeave.withPersonId(null).personId);
        assertNull(validLeave.withLeaveType(null).leaveType);
        assertNull(validLeave.withReplacementPersonId(null).replacementPersonId);
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
        assertNotEquals(validLeave, validLeave.withPersonId(null));
        assertNotEquals(validLeave, validLeave.withLeaveType(null));
        assertNotEquals(validLeave, validLeave.withReplacementPersonId(null));
        assertNotEquals(validLeave, validLeave.withStartTime(null));
        assertNotEquals(validLeave, validLeave.withEndTime(null));
        assertNotEquals(validLeave, validLeave.withDayCount(null));
        assertNotEquals(validLeave, validLeave.withDescription(null));
        assertNotEquals(validLeave, validLeave.withStatus(ApprovalStatus.Approved));
    }
}
