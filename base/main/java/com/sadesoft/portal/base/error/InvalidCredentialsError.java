package com.sadesoft.portal.base.error;

public class InvalidCredentialsError extends ClientError {
    public InvalidCredentialsError() {
        super("Invalid username or password.");
    }
}
