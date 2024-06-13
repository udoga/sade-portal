package com.sadesoft.portal.base.repo;

import java.util.Map;

import com.sadesoft.portal.base.model.Props;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JdbcConfigTest {
    @Test
    public void initializes() {
        Props props = new Props(Map.of("url", "url", "username", "u", "password", "p", "driver", "d"));
        JdbcConfig c = new JdbcConfig(props);
        assertEquals("url", c.url);
        assertEquals("u", c.username);
        assertEquals("p", c.password);
        assertEquals("d", c.driver);
    }

    @Test
    public void initializesWithDefaults() {
        Props props = new Props(Map.of());
        JdbcConfig c = new JdbcConfig(props);
        assertEquals("", c.url);
        assertEquals("", c.username);
        assertEquals("", c.password);
        assertEquals("", c.driver);
    }
}
