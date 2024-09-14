package com.sadesoft.portal.base.error;

public class AuthorizationError extends ClientError {
    public final String permissionName;

    public AuthorizationError(String permissionName) {
        super("Permission denied: '" + permissionName + "'");
        this.permissionName = permissionName;
    }
}
