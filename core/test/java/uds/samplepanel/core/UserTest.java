package uds.samplepanel.core;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
}
