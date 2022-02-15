package uds.samplepanel.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    public void initializesWithoutArgs() {
        User user = new User();
        assertNull(user.id);
        assertNull(user.username);
    }

    @Test
    public void initializesWithUsername() {
        User user = new User("sample");
        assertNull(user.id);
        assertEquals("sample", user.username);
        assertEquals("User[id=null username=sample]", user.toString());
    }
}
