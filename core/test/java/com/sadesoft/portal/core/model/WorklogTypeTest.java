package com.sadesoft.portal.core.model;

import com.sadesoft.portal.core.sample.WorklogTypeSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class WorklogTypeTest {
    private WorklogType validWorklogType;

    @BeforeEach
    public void setup() {
        validWorklogType= WorklogTypeSamples.validWorklogType;
    }

    @Test
    public void initializesWithoutFields() {
        WorklogType worklogType = new WorklogType();
        assertNull(worklogType.id);
        assertNull(worklogType.name);
    }

    @Test
    public void printsFields() {
        assertEquals("WorklogType[id=null" +
                " name=Meeting" +
                "]", validWorklogType.toString());
    }

    @Test
    public void clonesWithFields() {
        assertNull(validWorklogType.withId(null).id);
        assertNull(validWorklogType.withName(null).name);
    }

    @Test
    public void checksEquality() {
        assertEquals(validWorklogType, validWorklogType.copy());
        assertNotEquals(validWorklogType, validWorklogType.withId("1"));
        assertNotEquals(validWorklogType, validWorklogType.withName(null));
    }
}
