package com.sadesoft.portal.base.error;

public class InvalidCharacterError extends ClientError {
    public InvalidCharacterError(String field, char invalidChar, int index) {
        super("Field '" + field + "' has invalid char '" + invalidChar + "' at index: " + index + ".");
    }
}
