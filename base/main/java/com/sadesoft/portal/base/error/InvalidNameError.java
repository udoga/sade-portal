package com.sadesoft.portal.base.error;

public class InvalidNameError extends RuntimeException {
    public InvalidNameError(String fieldName, char invalidChar, int index) {
        super(fieldName + " has invalid char '" + invalidChar + "' at index: " + index + ".");
    }
}
