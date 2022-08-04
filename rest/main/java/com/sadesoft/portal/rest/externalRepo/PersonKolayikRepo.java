package com.sadesoft.portal.rest.externalRepo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.Person;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class PersonKolayikRepo implements Repo<Person> {
    @Override
    public List<Person> list() {
            JsonNode rootNode = getRootNode();
            JsonNode itemNodes = rootNode.findValue("data").findValue("items");
            List<Person> people = getListOfPeople(itemNodes);
            return people;
    }

    @Override
    public List<Person> listPage(int pageNo, int pageSize) {
        return list();
    }

    @Override
    public Person create(Person record) {
        return record;
    }

    @Override
    public Person read(int id) {
        return null;
    }

    @Override
    public Person update(int id, Person record) {
        return record;
    }

    @Override
    public Person delete(int id) {
        return null;
    }

    private JsonNode getRootNode() {
        HttpResponse<String> response = getHttpResponse();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(response.body());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpResponse<String> getHttpResponse() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = getHttpRequest();
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private List<Person> getListOfPeople(JsonNode itemNodes) {
        List<Person> people = new ArrayList<>();
        for (JsonNode node: itemNodes)
            people.add(createPerson(node));
        return people;
    }

    private HttpRequest getHttpRequest(){
        return HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/mock/person/list"))
                .setHeader("Authorization", "Bearer Whb6T8N5gXgdrFCcUIKONUM2ABXjiE47quxNyCbwhP3J")
                .method("POST", HttpRequest.BodyPublishers.noBody())
                .build();
    }

    private Person createPerson(JsonNode node) {
        return new Person()
                .withFirstName(node.findValue("firstName").asText())
                .withLastName(node.findValue("lastName").asText());
    }
}
