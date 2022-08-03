package com.sadesoft.portal.base.error;

public class InvalidLengthError extends InvalidInputError {
    public InvalidLengthError(int length, String field) {
        super("Invalid length '" + length + "' in field '" + field + "'.");
    }
}
