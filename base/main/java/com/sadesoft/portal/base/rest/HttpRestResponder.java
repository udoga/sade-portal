package com.sadesoft.portal.base.rest;

import com.fasterxml.jackson.databind.JsonNode;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRestResponder implements RestResponder {
    private final String serviceUrl;
    private final HttpClient client;
    private final JsonMapper mapper;

    public HttpRestResponder(String serviceUrl, JsonMapper mapper) {
        this.serviceUrl = serviceUrl;
        this.client = HttpClient.newHttpClient();
        this.mapper = mapper;
    }

    @Override
    public JsonNode respond(RestRequest r) {
        HttpRequest.BodyPublisher publisher = getPublisher(r.body);
        HttpRequest request = getHttpRequest(serviceUrl + r.url, r.method, publisher);
        HttpResponse<String> httpResponse = getHttpResponse(request);
        return mapper.stringToJson(httpResponse.body());
    }

    private HttpRequest.BodyPublisher getPublisher(JsonNode body) {
        return (body == null) ? HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofString(body.toString());
    }

    private HttpRequest getHttpRequest(String url, String method, HttpRequest.BodyPublisher publisher) {
        return HttpRequest.newBuilder().uri(URI.create(url)).method(method, publisher).build();
    }

    private HttpResponse<String> getHttpResponse(HttpRequest request) {
        try { return client.send(request, HttpResponse.BodyHandlers.ofString()); }
        catch (Exception e) { throw new RuntimeException(e); }
    }
}
