package com.sadesoft.portal.base.error;

public class InvalidCharacterError extends RuntimeException {
    public InvalidCharacterError(String fieldName, char invalidChar, int index) {
        super(fieldName + " has invalid char '" + invalidChar + "' at index: " + index + ".");
    }
}
