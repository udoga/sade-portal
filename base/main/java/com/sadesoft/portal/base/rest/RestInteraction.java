package com.sadesoft.portal.base.rest;

import com.fasterxml.jackson.databind.JsonNode;

public class RestInteraction {
    public final RestRequest request;
    public final JsonNode response;

    public RestInteraction(RestRequest request, JsonNode response) {
        this.request = request;
        this.response = response;
    }
}
