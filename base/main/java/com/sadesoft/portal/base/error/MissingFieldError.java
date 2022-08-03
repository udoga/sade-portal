package com.sadesoft.portal.base.error;

public class MissingFieldError extends InvalidInputError {
    public MissingFieldError(String field) {
        super("Field '" + field + "' field is required.");
    }
}
