package com.sadesoft.portal.base.config;

import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseConfigTest {
    @Test
    public void initializes() {
        Props props = new Props(Map.of("url", "url", "username", "u", "password", "p", "driver", "d"));
        DatabaseConfig c = new DatabaseConfig(props);
        assertEquals("url", c.url);
        assertEquals("u", c.username);
        assertEquals("p", c.password);
        assertEquals("d", c.driver);
    }
}
