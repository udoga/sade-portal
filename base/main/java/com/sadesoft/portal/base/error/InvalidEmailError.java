package com.sadesoft.portal.base.error;

public class InvalidEmailError extends InvalidInputError {
    public InvalidEmailError(String value, String field) {
        super("Invalid email '" + value + "' in field '" + field + "'.");
    }
}
