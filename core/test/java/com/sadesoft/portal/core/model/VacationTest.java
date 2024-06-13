package com.sadesoft.portal.core.model;

import com.sadesoft.portal.core.sample.VacationSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VacationTest {
    private Vacation validVacation;

    @BeforeEach
    public void setup() {
        validVacation = VacationSamples.validVacation.copy();
    }

    @Test
    public void initializesWithoutFields() {
        Vacation l = new Vacation();
        assertNull(l.id);
        assertNull(l.employeeId);
        assertNull(l.vacationType);
        assertNull(l.replacementEmployeeId);
        assertNull(l.startTime);
        assertNull(l.endTime);
        assertNull(l.dayCount);
        assertNull(l.description);
        assertNull(l.status);
    }

    @Test
    public void printsFields() {
        assertEquals("Vacation[id=null" +
                " employeeId=4" +
                " vacationType=AnnualVacation" +
                " replacementEmployeeId=6" +
                " startTime=2022-05-04T09:18" +
                " endTime=2022-05-04T09:19" +
                " dayCount=descriptionExample" +
                " description=descriptionExample" +
                " status=null]", validVacation.toString());
    }

    @Test
    public void clonesWithFields() {
        assertNull(validVacation.withId(null).id);
        assertNull(validVacation.withEmployeeId(null).employeeId);
        assertNull(validVacation.withVacationType(null).vacationType);
        assertNull(validVacation.withReplacementEmployeeId(null).replacementEmployeeId);
        assertNull(validVacation.withStartTime(null).startTime);
        assertNull(validVacation.withEndTime(null).endTime);
        assertNull(validVacation.withDayCount(null).dayCount);
        assertNull(validVacation.withDescription(null).description);
        assertNull(validVacation.withStatus(null).status);
    }

    @Test
    public void checksEquality() {
        assertEquals(validVacation, validVacation.copy());
        assertNotEquals(validVacation, validVacation.withId("0"));
        assertNotEquals(validVacation, validVacation.withEmployeeId(null));
        assertNotEquals(validVacation, validVacation.withVacationType(null));
        assertNotEquals(validVacation, validVacation.withReplacementEmployeeId(null));
        assertNotEquals(validVacation, validVacation.withStartTime(null));
        assertNotEquals(validVacation, validVacation.withEndTime(null));
        assertNotEquals(validVacation, validVacation.withDayCount(null));
        assertNotEquals(validVacation, validVacation.withDescription(null));
        assertNotEquals(validVacation, validVacation.withStatus(ApprovalStatus.Approved));
    }
}
