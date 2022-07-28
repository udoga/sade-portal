package com.sadesoft.portal.base.error;

public class CopiedFieldError extends RuntimeException{
    public CopiedFieldError(String fieldName1, String fieldName2) {
        super("Fields '" + fieldName1 + "' and '" + fieldName2 + "' should not be the same.");
    }
}
