package com.sadesoft.portal.base.error;

public class EmptyFieldError extends RuntimeException{
    public EmptyFieldError(String typeName, String fieldName) {
        super(typeName + " '" + fieldName + "' field is empty.");
    }
}
