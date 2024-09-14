package com.sadesoft.portal.base.auth;

import com.sadesoft.portal.base.error.AuthorizationError;

import java.util.ArrayList;
import java.util.List;

public class MockAuthorizer implements Authorizer {
    public List<String> callerIds = new ArrayList<>();
    public List<String> permissionNames = new ArrayList<>();

    @Override
    public void authorize(String callerId, String requestName) {
        for (String name: permissionNames) if (callerIds.contains(callerId) && requestName.equals(name)) return;
        throw new AuthorizationError(requestName);
    }
}
