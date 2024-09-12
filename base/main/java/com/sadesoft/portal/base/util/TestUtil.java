package com.sadesoft.portal.base.util;

import org.junit.jupiter.api.function.Executable;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestUtil {
    public static <T extends Throwable> void assertException(Class<T> type, String message, Executable e) {
        T exception = assertThrows(type, e);
        assertEquals(message, exception.getMessage());
    }

    public static void assertInvalidInput(Executable e) {
        assertThrows(InvalidInputError.class, e);
    }

    public static void assertMissingField(Executable e) {
        assertThrows(MissingFieldError.class, e);
    }
}
