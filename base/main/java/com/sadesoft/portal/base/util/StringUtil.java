package com.sadesoft.portal.base.util;

import com.sadesoft.portal.base.error.InvalidNumberError;

public class StringUtil {
    public static Integer getInteger(String value) {
        try { return Integer.parseInt(value); }
        catch (NumberFormatException e) { throw new InvalidNumberError(value); }
    }
}
