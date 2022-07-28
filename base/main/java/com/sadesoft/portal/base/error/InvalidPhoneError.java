package com.sadesoft.portal.base.error;

public class InvalidPhoneError extends RuntimeException{
    public InvalidPhoneError(String phone, String fieldName) {
        super("Invalid phone number '" + phone + "' in field '" + fieldName + "'.");
    }
}
