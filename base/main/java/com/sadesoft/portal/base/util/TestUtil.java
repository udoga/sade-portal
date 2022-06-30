package com.sadesoft.portal.base.util;

import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestUtil {
    public static <T extends Throwable> void assertException(Class<T> type, String message, Executable e) {
        T exception = assertThrows(type, e);
        assertEquals(message, exception.getMessage());
    }
}
