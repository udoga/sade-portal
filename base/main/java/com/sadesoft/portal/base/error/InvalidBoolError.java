package com.sadesoft.portal.base.error;

public class InvalidBoolError extends ClientError{
    public InvalidBoolError(String value) {
        super("Value '" + value + "' should be true or false.");
    }
}
