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
        assertNull(user.birthDate);
    }

    @Test
    public void initializesWithFields() {
        User user = new User("sample", LocalDate.of(2000, 1, 1));
        assertNull(user.id);
        assertEquals("sample", user.username);
        assertEquals(LocalDate.of(2000, 1, 1), user.birthDate);
        assertEquals("User[id=null username=sample birthDate=2000-01-01]", user.toString());
    }
}
