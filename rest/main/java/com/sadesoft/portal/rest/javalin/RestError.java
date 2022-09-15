package com.sadesoft.portal.rest.javalin;

public class RestError {
    public int status;
    public String type;
    public String message;

    public RestError(int status, String type, String message) {
        this.status = status;
        this.type = type;
        this.message = message;
    }
}
