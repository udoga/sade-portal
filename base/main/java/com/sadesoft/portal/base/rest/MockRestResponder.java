package com.sadesoft.portal.base.rest;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MockRestResponder implements RestResponder {
    private final String url;
    private final List<RestInteraction> interactions;
    private final List<RestRequest> requests;

    public MockRestResponder(String url) {
        this.url = url;
        this.interactions = new ArrayList<>();
        this.requests = new ArrayList<>();
    }

    @Override
    public JsonNode respond(RestRequest r) {
        requests.add(r);
        for (RestInteraction i : interactions)
            if (Objects.equals(r, i.request))
                return i.response;
        return null;
    }

    public void save(String url, String method, JsonNode body, JsonNode response) {
        interactions.add(new RestInteraction(new RestRequest(this.url + url, method, body), response));
    }

    public RestInteraction getInteraction(int i) {
        return interactions.get(i);
    }

    public RestRequest getRequest(int i) {
        return requests.get(i);
    }

    public int getRequestCount() {
        return requests.size();
    }
}
