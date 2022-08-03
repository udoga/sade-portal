package com.sadesoft.portal.base.error;

public class InvalidDateError extends InvalidInputError {
    public InvalidDateError(String value) {
        super("Date input '" + value + "' should have 'YYYY-MM-DD' format.");
    }
}
