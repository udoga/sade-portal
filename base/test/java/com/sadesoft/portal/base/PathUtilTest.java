package com.sadesoft.portal.base;

import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PathUtilTest {
    @Test
    public void whenResourceFound_findsAbsolutePath() {
        String path = PathUtil.findResourcePath("test.properties");
        assertTrue(Paths.get(path).isAbsolute());
    }

    @Test
    public void whenResourceNotFound_throwsError() {
        TestUtil.assertException(RuntimeException.class, "Resource not found: x", () -> PathUtil.findResourcePath("x"));
    }
}
