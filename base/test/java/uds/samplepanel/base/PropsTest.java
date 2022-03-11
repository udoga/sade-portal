package uds.samplepanel.base;

import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PropsTest {
    @Test
    public void initializesWithMap() {
        Props props = new Props(Map.of("One", "1", "Two", "2"));
        assertEquals(2, props.count());
        assertEquals("1", props.get("One"));
        assertEquals("2", props.get("Two"));
    }

    @Test
    public void initializesWithFilePath() {
        Props props = new Props(PathUtil.findResourcePath("test.properties"));
        assertEquals(2, props.count());
        assertEquals("value1", props.get("key1"));
        assertEquals("value2", props.get("key2"));
    }

    @Test
    public void findsStringProperties() {
        Props props = new Props(Map.of("key1", "value1", "key2", "value2"));
        assertEquals("value1", props.get("key1"));
        assertEquals("value2", props.get("key2"));
        assertThrows(RuntimeException.class, () -> props.get("x"), "Property not found: key=x");
    }

    @Test
    public void findsIntegerProperties() {
        Props props = new Props(Map.of("key1", "value1", "number1", "1", "number2", "2"));
        assertEquals(1, props.getInt("number1"));
        assertEquals(2, props.getInt("number2"));
        assertThrows(NumberFormatException.class, () -> props.getInt("key1"));
    }

    @Test
    public void findsBooleanProperties() {
        Props props = new Props(Map.of("a", "true", "b", "false", "c", "invalid"));
        assertTrue(props.getBool("a"));
        assertFalse(props.getBool("b"));
        assertThrows(RuntimeException.class, () -> props.getBool("c"));
    }

    @Test
    public void findsSection() {
        Props props = new Props(Map.of("key1", "value1", "section.a", "1", "section.b", "2"));
        Props section = props.getSection("section");
        assertEquals(2, section.count());
        assertEquals("1", section.get("a"));
        assertEquals("2", section.get("b"));
    }
}
