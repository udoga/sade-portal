package com.sadesoft.portal.base.error;

public class MissingFieldError extends ClientError {
    public MissingFieldError(String field) {
        super("Field '" + field + "' field is required.");
    }
}
