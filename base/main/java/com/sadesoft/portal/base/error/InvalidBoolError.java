package com.sadesoft.portal.base.error;

public class InvalidBoolError extends InvalidInputError {
    public InvalidBoolError(String value) {
        super("Value '" + value + "' should be true or false.");
    }
}
