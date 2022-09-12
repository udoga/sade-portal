package com.sadesoft.portal.core.model;

import com.sadesoft.portal.core.sample.PermissionSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PermissionTest {
    private Permission validPermission;

    @BeforeEach
    public void setup() {
        validPermission = PermissionSamples.validPermission;
    }

    @Test
    public void initializesWithoutFields() {
        Permission permission = new Permission();
        assertNull(permission.id);
        assertNull(permission.name);
    }

    @Test
    public void printsFields() {
        assertEquals("Permission[id=null" +
                " name=person_create" +
                "]", validPermission.toString());
    }

    @Test
    public void clonesWithFields() {
        assertNull(validPermission.withId(null).id);
        assertNull(validPermission.withName(null).name);
    }

    @Test
    public void checksEquality() {
        assertEquals(validPermission, validPermission.copy());
        assertNotEquals(validPermission, validPermission.withId("1"));
        assertNotEquals(validPermission, validPermission.withName(null));
    }
}
