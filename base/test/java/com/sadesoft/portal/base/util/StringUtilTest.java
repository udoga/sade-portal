package com.sadesoft.portal.base.util;

import com.sadesoft.portal.base.error.InvalidNumberError;
import org.junit.jupiter.api.Test;

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

    @Test
    public void hashesSha256() {
        assertEquals("6eac1114aa783f6549327e7d01f63752995da7b31f1f37092b7dcb9f49cf5651", StringUtil.hashSha256("pwd1"));
        assertEquals("149d2937d1bce53fa683ae652291bd54cc8754444216a9e278b45776b76375af", StringUtil.hashSha256("pwd2"));
    }
}
