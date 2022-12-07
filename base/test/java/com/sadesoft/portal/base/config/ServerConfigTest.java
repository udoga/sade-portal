package com.sadesoft.portal.base.config;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ServerConfigTest {
    @Test
    public void initializesWithDefaults() {
        ServerConfig config = new ServerConfig(new Props(Map.of()));
        assertEquals("", config.path);
        assertEquals(7000, config.port);
        assertFalse(config.uiExternal);
        assertEquals("", config.uiFolder);
        assertEquals(0, config.sessionTimeout);
        assertFalse(config.authenticationEnabled);
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
        ServerConfig config = new ServerConfig(props);
        assertEquals("/api", config.path);
        assertEquals(1234, config.port);
        assertTrue(config.uiExternal);
        assertEquals("ui", config.uiFolder);
        assertEquals(1000, config.sessionTimeout);
        assertTrue(config.authenticationEnabled);
    }
}
