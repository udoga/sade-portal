package uds.samplepanel.base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropsTest {
    @Test
    public void whenInitializedWithoutArguments_isEmpty() {
        assertEquals(0, new Props().count());
    }

    @Test
    public void whenInitializedWithPath_loadsProperties() {
        Resource resource = new Resource("test.properties");
        Props props = new Props(resource.findPath());
        assertEquals(2, props.count());
        assertEquals("value1", props.get("key1"));
        assertEquals("value2", props.get("key2"));
    }
}
