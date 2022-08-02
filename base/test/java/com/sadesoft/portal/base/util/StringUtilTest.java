package com.sadesoft.portal.base.util;

import com.sadesoft.portal.base.error.InvalidNumberError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilTest {
    @Test
    public void whenNumberIsInvalid_throwsError() {
        Class<InvalidNumberError> errorClass = InvalidNumberError.class;
        TestUtil.assertException(errorClass, "Value 'x' should be numeric.", () -> StringUtil.getInteger("x"));
        TestUtil.assertException(errorClass, "Value '1.12' should be numeric.", () -> StringUtil.getInteger("1.12"));
        TestUtil.assertException(errorClass, "Value 'a12' should be numeric.", () -> StringUtil.getInteger("a12"));
    }

    @Test
    public void whenNumberIsValid_parsesNumber() {
        assertEquals(StringUtil.getInteger("1"), 1);
        assertEquals(StringUtil.getInteger("10"), 10);
        assertEquals(StringUtil.getInteger("0"), 0);
    }
}
