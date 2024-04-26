package com.sadesoft.portal.base.model;

import java.util.Map;
import com.sadesoft.portal.base.util.PathUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PropsTest {
    @Test
    public void initializesWithMap() {
        Props props = new Props(Map.of("One", "1", "Two", "2"));
        assertEquals(2, props.count());
        assertEquals("1", props.getString("One"));
        assertEquals("2", props.getString("Two"));
    }

    @Test
    public void initializesWithFilePath() {
        Props props = new Props(PathUtil.findResourcePath("test.properties"));
        assertEquals(2, props.count());
        assertEquals("value1", props.getString("key1"));
        assertEquals("value2", props.getString("key2"));
    }

    @Test
    public void findsString() {
        Props props = new Props(Map.of("key1", "value1", "key2", "value2"));
        assertEquals("value1", props.getString("key1"));
        assertEquals("value2", props.getString("key2"));
        assertThrows(RuntimeException.class, () -> props.getString("x"), "Property not found: key=x");
    }

    @Test
    public void findsStringOrDefault() {
        Props props = new Props(Map.of("key1", "value1"));
        assertEquals("value1", props.getString("key1", "x"));
        assertEquals("x", props.getString("key2", "x"));
    }

    @Test
    public void findsInteger() {
        Props props = new Props(Map.of("key1", "value1", "number1", "1", "number2", "2"));
        assertEquals(1, props.getInt("number1"));
        assertEquals(2, props.getInt("number2"));
        assertThrows(NumberFormatException.class, () -> props.getInt("key1"));
    }

    @Test
    public void findsIntegerOrDefault() {
        Props props = new Props(Map.of("key1", "value1", "number1", "1", "number2", "2"));
        assertEquals(1, props.getInt("number1", 10));
        assertEquals(10, props.getInt("number3", 10));
        assertThrows(NumberFormatException.class, () -> props.getInt("key1", 10));
    }

    @Test
    public void findsBoolean() {
        Props props = new Props(Map.of("a", "true", "b", "false", "c", "invalid"));
        assertTrue(props.getBool("a"));
        assertFalse(props.getBool("b"));
        assertThrows(RuntimeException.class, () -> props.getBool("c"));
    }

    @Test
    public void findsBooleanOrDefault() {
        Props props = new Props(Map.of("a", "true", "b", "false", "c", "invalid"));
        assertTrue(props.getBool("a", false));
        assertFalse(props.getBool("d", false));
        assertThrows(RuntimeException.class, () -> props.getBool("c", true));
    }

    @Test
    public void findsSection() {
        Props props = new Props(Map.of("key1", "value1", "section.a", "1", "section.b", "2"));
        Props section = props.getSection("section");
        assertEquals(2, section.count());
        assertEquals("1", section.getString("a"));
        assertEquals("2", section.getString("b"));
    }
}
