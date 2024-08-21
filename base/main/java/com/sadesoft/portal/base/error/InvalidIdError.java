package com.sadesoft.portal.base.error;

public class InvalidIdError extends InvalidInputError {
    public InvalidIdError(String id) {
        super("Invalid record id: " + id);
    }
}
