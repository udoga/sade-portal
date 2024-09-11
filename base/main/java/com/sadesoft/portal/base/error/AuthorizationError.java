package com.sadesoft.portal.base.error;

public class AuthorizationError extends ClientError {
    public final String requestName;

    public AuthorizationError(String requestName) {
        super("Permission denied for '" + requestName + "' request.");
        this.requestName = requestName;
    }
}
