package com.sadesoft.portal.base.rest;

import com.fasterxml.jackson.databind.JsonNode;

public interface RestResponder {
    JsonNode respond(String url, String method, String body);
}
