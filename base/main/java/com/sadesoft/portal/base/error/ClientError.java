package com.sadesoft.portal.base.error;

public class ClientError extends RuntimeException {
    public ClientError(String message) {
        super(message);
    }
}
