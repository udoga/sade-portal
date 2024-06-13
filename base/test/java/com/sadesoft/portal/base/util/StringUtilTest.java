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
    public void hashesSha512() {
        assertEquals("94af3fa5261b347f098bd9cf0fc1c145a20e1f662cb21b0d4a763398ac886f19017cb7d8bfd71" +
            "df689108511f6f8d0c1ab464a80620d4332379d544ba67131a0", StringUtil.hashSha512("pwd1"));
        assertEquals("3ebd594bccb9f9e076cd90eea1f6c46efef9a78a7b58f13fe89d2184160027a3fb6ca1d81073b" +
            "b64923f3a4fd6b456f04c70a0881827ce43bcfd2642ed93b3d8", StringUtil.hashSha512("pwd2"));
    }
}
