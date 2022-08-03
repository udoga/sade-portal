package com.sadesoft.portal.base.error;

public class InvalidPhoneError extends InvalidInputError {
    public InvalidPhoneError(String value, String field) {
        super("Invalid phone number '" + value + "' in field '" + field + "'.");
    }
}
