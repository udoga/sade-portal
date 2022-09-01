package com.sadesoft.portal.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestClient {
    private final String serviceUrl;
    private final HttpClient client;
    private final ObjectMapper mapper;

    public RestClient(String serviceUrl) {
        this.serviceUrl = serviceUrl;
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public JsonNode respond(String url, String method, String body) {
        HttpRequest.BodyPublisher publisher = getPublisher(body);
        HttpRequest request = getHttpRequest(serviceUrl + url, method, publisher);
        HttpResponse<String> httpResponse = getHttpResponse(request);
        return getRootNode(httpResponse);
    }

    private HttpRequest.BodyPublisher getPublisher(String body) {
        return (body == null) ? HttpRequest.BodyPublishers.noBody() : HttpRequest.BodyPublishers.ofString(body);
    }

    private HttpRequest getHttpRequest(String url, String method, HttpRequest.BodyPublisher publisher) {
        return HttpRequest.newBuilder().uri(URI.create(url)).method(method, publisher).build();
    }

    private HttpResponse<String> getHttpResponse(HttpRequest request) {
        try { return client.send(request, HttpResponse.BodyHandlers.ofString()); }
        catch (Exception e) { throw new RuntimeException(e); }
    }

    private JsonNode getRootNode(HttpResponse<String> response) {
        try { return mapper.readTree(response.body()); }
        catch (JsonProcessingException e) { throw new RuntimeException(e); }
    }
}
