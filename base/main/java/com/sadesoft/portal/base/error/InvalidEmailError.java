package com.sadesoft.portal.base.error;

public class InvalidEmailError extends RuntimeException{
    public InvalidEmailError(String email, String fieldName) {
        super("Invalid email '" + email + "' in field '" + fieldName + "'.");
    }
}
