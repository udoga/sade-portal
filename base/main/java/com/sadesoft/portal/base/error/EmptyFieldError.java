package com.sadesoft.portal.base.error;

public class EmptyFieldError extends InvalidInputError {
    public EmptyFieldError(String field) {
        super("Field '" + field + "' should not be empty.");
    }
}
