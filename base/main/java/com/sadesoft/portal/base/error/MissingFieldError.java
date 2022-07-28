package com.sadesoft.portal.base.error;

public class MissingFieldError extends RuntimeException {
    public MissingFieldError(String field) {
        super("Field '" + field + "' field is required.");
    }
}
