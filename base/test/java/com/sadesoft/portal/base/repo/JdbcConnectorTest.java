package com.sadesoft.portal.base.repo;

import java.util.Map;

import com.sadesoft.portal.base.model.Props;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JdbcConnectorTest {
    @Test
    public void initializes() {
        Props props = new Props(Map.of("url", "/", "username", "u", "password", "p", "driver", "d", "init_path", "i"));
        JdbcConnector c = new JdbcConnector(props);
        assertEquals("/", c.url);
        assertEquals("u", c.username);
        assertEquals("p", c.password);
        assertEquals("d", c.driver);
        assertEquals("d", c.driver);
        assertEquals("i", c.initPath);
    }

    @Test
    public void initializesWithDefaults() {
        Props props = new Props(Map.of());
        JdbcConnector c = new JdbcConnector(props);
        assertEquals("", c.url);
        assertEquals("", c.username);
        assertEquals("", c.password);
        assertEquals("", c.driver);
        assertEquals("", c.initPath);
    }
}
