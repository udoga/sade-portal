package com.sadesoft.portal.repo.kolay;

import java.util.Map;
import com.sadesoft.portal.base.config.Props;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KolayConfigTest {
    @Test
    void initializes() {
        Props props = new Props(Map.of("enabled", "true", "url", "sample-url", "token", "sample-token"));
        KolayConfig config = new KolayConfig(props);
        assertTrue(config.enabled);
        assertEquals("sample-url", config.url);
        assertEquals("sample-token", config.token);
    }

    @Test
    void initializesWithDefaults() {
        Props props = new Props(Map.of());
        KolayConfig config = new KolayConfig(props);
        assertFalse(config.enabled);
        assertEquals("", config.url);
        assertEquals("", config.token);
    }
}
