package com.sadesoft.portal.base.error;

public class MissingFieldError extends RuntimeException {
    public MissingFieldError(String typeName, String fieldName) {
        super(typeName + " '" + fieldName + "' field is required.");
    }
}
