package com.sadesoft.portal.base.error;

public class InvalidNumberError extends InvalidInputError {
    public InvalidNumberError(String value) {
        super("Value '" + value + "' should be numeric.");
    }
}
