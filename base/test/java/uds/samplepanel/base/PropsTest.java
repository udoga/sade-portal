package uds.samplepanel.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PropsTest {
    @Test
    public void whenInitializedWithoutArguments_isEmpty() {
        assertEquals(0, new Props().count());
    }

    @Nested
    public class GivenLoadedProps {
        Props props;

        @BeforeEach
        public void setup() {
            props = new Props(PathUtil.findResourcePath("test.properties"));
        }

        @Test
        public void isNotEmpty() {
            assertNotEquals(0, props.count());
        }

        @Test
        public void findsStringProperties() {
            assertEquals("value1", props.get("key1"));
            assertEquals("value2", props.get("key2"));
            assertThrows(RuntimeException.class, () -> props.get("x"), "Property not found: key=x");
        }

        @Test
        public void findsIntegerProperties() {
            assertEquals(1, props.getInt("number1"));
            assertEquals(2, props.getInt("number2"));
            assertThrows(RuntimeException.class, () -> props.getInt("x"), "Property not found: key=x");
            assertThrows(NumberFormatException.class, () -> props.getInt("key1"));
        }
    }
}
