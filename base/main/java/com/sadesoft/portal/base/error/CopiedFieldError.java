package com.sadesoft.portal.base.error;

public class CopiedFieldError extends InvalidInputError {
    public CopiedFieldError(String field1, String field2) {
        super("Fields '" + field1 + "' and '" + field2 + "' should not be the same.");
    }
}
