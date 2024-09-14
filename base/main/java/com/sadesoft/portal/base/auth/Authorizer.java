package com.sadesoft.portal.base.auth;

import java.util.List;

public interface Authorizer {
    void authorize(String callerId, String operation, List<String> flags);
}
