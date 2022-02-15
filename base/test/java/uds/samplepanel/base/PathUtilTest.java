package uds.samplepanel.base;

import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static uds.samplepanel.base.TestUtil.assertException;

public class PathUtilTest {
    @Test
    public void whenResourceFound_findsAbsolutePath() {
        String path = PathUtil.findResourcePath("test.properties");
        assertTrue(Paths.get(path).isAbsolute());
    }

    @Test
    public void whenResourceNotFound_throwsError() {
        assertException(RuntimeException.class, "Resource not found: x", () -> PathUtil.findResourcePath("x"));
    }
}
