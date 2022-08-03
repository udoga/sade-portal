package com.sadesoft.portal.base.error;

public class InvalidInputError extends RuntimeException {
    public InvalidInputError(String message) {
        super(message);
    }
}
