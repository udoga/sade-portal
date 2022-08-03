package com.sadesoft.portal.base.error;

public class InvalidEnumError extends InvalidInputError {
    public InvalidEnumError(String type, String value) {
        super("Invalid " + type + " '" + value + "'.");
    }
}
