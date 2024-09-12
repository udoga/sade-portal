package com.sadesoft.portal.base.auth;

public interface Authorizer {
    void authorize(String callerId, String requestName);
}
