package com.sadesoft.portal.core.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    public void initializesWithoutFields() {
        User user = new User();
        assertNull(user.id);
        assertNull(user.username);
        assertNull(user.fullName);
    }

    @Test
    public void initializesWithFields() {
        User user = new User("username", "Full Name");
        assertNull(user.id);
        assertEquals("username", user.username);
        assertEquals("Full Name", user.fullName);
    }

    @Test
    public void clonesWithFields() {
        User user = new User("username", "Full Name");
        assertEquals(1, user.withId(1).id);
        assertEquals("username", user.withId(1).username);
        assertEquals("new_username", user.withUsername("new_username").username);
        assertEquals("new full name", user.withFullName("new full name").fullName);
        assertNull(user.withFullName("new full name").id);
        assertNull(user.withFullName(null).fullName);
    }
}
