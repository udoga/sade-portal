package uds.samplepanel.base;

import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServerConfigTest {
    @Test
    public void initializes() {
        Props props = new Props(Map.of("port", "7000", "ui_external", "true", "ui_folder", "ui"));
        ServerConfig config = new ServerConfig(props);
        assertEquals(7000, config.port);
        assertTrue(config.uiExternal);
        assertEquals("ui", config.uiFolder);
    }
}
