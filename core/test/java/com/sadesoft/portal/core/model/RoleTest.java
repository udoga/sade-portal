package com.sadesoft.portal.core.model;

import com.sadesoft.portal.core.sample.RoleSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RoleTest {
    private Role validRole;

    @BeforeEach
    public void setup() {
        validRole= RoleSamples.validRole;
    }

    @Test
    public void initializesWithoutFields() {
        Role role = new Role();
        assertNull(role.id);
        assertNull(role.name);
    }

    @Test
    public void printsFields() {
        assertEquals("Role[id=null" +
                " name=admin" +
                "]", validRole.toString());
    }

    @Test
    public void clonesWithFields() {
        assertNull(validRole.withId(null).id);
        assertNull(validRole.withName(null).name);
    }

    @Test
    public void checksEquality() {
        assertEquals(validRole, validRole.copy());
        assertNotEquals(validRole, validRole.withId("1"));
        assertNotEquals(validRole, validRole.withName(null));
    }
}

