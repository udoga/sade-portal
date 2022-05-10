package uds.samplepanel.base;

import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseConnectorTest {
    @Test
    public void initializes() {
        Props props = new Props(Map.of("url", "url", "username", "u", "password", "p", "driver", "d"));
        DatabaseConnector c = new DatabaseConnector(props);
        assertEquals("url", c.url);
        assertEquals("u", c.username);
        assertEquals("p", c.password);
        assertEquals("d", c.driver);
    }
}
