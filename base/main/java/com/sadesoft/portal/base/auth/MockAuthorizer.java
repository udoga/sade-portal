package com.sadesoft.portal.base.auth;

import com.sadesoft.portal.base.error.AuthorizationError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MockAuthorizer implements Authorizer {
    public List<String> callerIds = new ArrayList<>();
    public List<String> permissionNames = new ArrayList<>();

    @Override
    public void authorize(String callerId, String operation, List<String> flags) {
        for (String permissionName: getPermissionNames(operation, flags))
            if (!callerIds.contains(callerId) || !permissionNames.contains(permissionName))
                throw new AuthorizationError(permissionName);
    }

    private List<String> getPermissionNames(String operation, List<String> flags) {
        return Stream.concat(Stream.of(operation), flags.stream().map(flag -> operation + "-" + flag)).toList();
    }
}
