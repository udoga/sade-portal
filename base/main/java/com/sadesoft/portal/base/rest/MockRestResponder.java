package com.sadesoft.portal.base.rest;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MockRestResponder implements RestResponder {
    private final List<RestInteraction> interactions = new ArrayList<>();
    private final List<RestRequest> requests = new ArrayList<>();

    @Override
    public JsonNode respond(RestRequest r) {
        requests.add(r);
        for (RestInteraction interaction : interactions)
            if (Objects.equals(r, interaction.request))
                return interaction.response;
        return null;
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

    public void save(RestRequest r, String response) {
        interactions.add(new RestInteraction(r, response));
    }
}
