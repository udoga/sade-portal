package com.sadesoft.portal.base.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestInteraction {
    public final RestRequest request;
    public final JsonNode response;

    public RestInteraction(RestRequest r, String response) {
        this.request = r;
        this.response = toJsonNode(response);
    }

    private JsonNode toJsonNode(String response) {
        try {
            return new ObjectMapper().readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
