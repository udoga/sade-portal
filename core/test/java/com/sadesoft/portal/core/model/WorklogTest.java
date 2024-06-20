package com.sadesoft.portal.core.model;

import com.sadesoft.portal.core.sample.WorklogSamples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class WorklogTest {
    private Worklog validWorklog;

    @BeforeEach
    public void setup() {
        validWorklog = WorklogSamples.validWorklog;
    }

    @Test
    public void initializesWithoutFields() {
        Worklog worklog = new Worklog();
        assertNull(worklog.id);
        assertNull(worklog.typeId);
        assertNull(worklog.employeeId);
        assertNull(worklog.projectId);
        assertNull(worklog.workDate);
        assertEquals(0, worklog.duration);
        assertNull(worklog.status);
        assertNull(worklog.description);
    }

    @Test
    public void printsFields() {
        assertEquals("Worklog[id=null" +
                " typeId=1" +
                " employeeId=1" +
                " projectId=1" +
                " workDate=2024-01-01" +
                " duration=2" +
                " status=Approved" +
                " description=Sample Worklog" +
                "]", validWorklog.toString());
    }

    @Test
    public void clonesWithFields() {
        assertNull(validWorklog.withId(null).id);
        assertNull(validWorklog.withTypeId(null).typeId);
        assertNull(validWorklog.withEmployeeId(null).employeeId);
        assertNull(validWorklog.withProjectId(null).projectId);
        assertNull(validWorklog.withWorkDate(null).workDate);
        assertEquals(0, validWorklog.withDuration(0).duration);
        assertNull(validWorklog.withStatus(null).status);
        assertNull(validWorklog.withDescription(null).description);
    }

    @Test
    public void checksEquality() {
        assertEquals(validWorklog, validWorklog.copy());
        assertNotEquals(validWorklog, validWorklog.withId("1"));
        assertNotEquals(validWorklog, validWorklog.withTypeId("2"));
        assertNotEquals(validWorklog, validWorklog.withEmployeeId("2"));
        assertNotEquals(validWorklog, validWorklog.withProjectId("2"));
        assertNotEquals(validWorklog, validWorklog.withWorkDate(LocalDate.of(2024, 1, 2)));
        assertNotEquals(validWorklog, validWorklog.withDuration(3));
        assertNotEquals(validWorklog, validWorklog.withStatus("Rejected"));
        assertNotEquals(validWorklog, validWorklog.withDescription("Different Worklog"));
    }
}

