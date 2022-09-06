package com.sadesoft.portal.base.rest;

import com.fasterxml.jackson.databind.JsonNode;

public interface RestResponder {
    JsonNode respond(RestRequest request);
}
