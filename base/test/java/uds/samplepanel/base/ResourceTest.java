package uds.samplepanel.base;

import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResourceTest {
    @Test
    public void whenResourceFound_findsAbsolutePath() {
        Resource resource = new Resource("test.properties");
        String path = resource.findPath();
        assertTrue(Paths.get(path).isAbsolute());
    }

    @Test
    public void whenResourceNotFound_throwsError() {
        Resource resource = new Resource("missing.properties");
        assertThrows(RuntimeException.class, resource::findPath);
    }
}
