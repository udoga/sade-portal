package com.sadesoft.portal.base.rest;

import com.sadesoft.portal.base.model.Props;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RestConfigTest {
    @Test
    public void initializesWithDefaults() {
        RestConfig config = new RestConfig(new Props(Map.of()));
        assertEquals("", config.path);
        assertEquals(7000, config.port);
        assertFalse(config.uiExternal);
        assertEquals("", config.uiFolder);
    }

    @Test
    public void initializesWithProps() {
        Props props = new Props(Map.of(
                "path", "/api",
                "port", "1234",
                "ui_external", "true",
                "ui_folder", "ui",
                "session_timeout", "1000",
                "authentication_enabled","true"));
        RestConfig config = new RestConfig(props);
        assertEquals("/api", config.path);
        assertEquals(1234, config.port);
        assertTrue(config.uiExternal);
        assertEquals("ui", config.uiFolder);
    }
}
