package com.sadesoft.portal.rest.externalRepo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.core.model.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonKolayikRepo implements Repo<Person> {
    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpClient client = HttpClient.newHttpClient();
    private final String serviceUrl = "http://localhost:8080/person/";

    @Override
    public List<Person> list() {
        return getPeople(fetchNode(serviceUrl + "list", "POST", "items"));
    }

    @Override
    public List<Person> listPage(int pageNo, int pageSize) {
        return list();
    }

    @Override
    public Person create(Person record) {
        return null;
    }

    @Override
    public Person read(int id) {
        return getPerson(fetchNode(serviceUrl + "view/" + id, "GET", "person"));
    }

    @Override
    public Person update(int id, Person record) {
        return null;
    }

    @Override
    public Person delete(int id) {
        return null;
    }

    private JsonNode fetchNode(String url, String method, String nodeName) {
        HttpRequest request = getHttpRequest(url, method);
        HttpResponse<String> httpResponse = getHttpResponse(request);
        JsonNode rootNode = getRootNode(httpResponse);
        return rootNode.findValue("data").findValue(nodeName);
    }

    private JsonNode getRootNode(HttpResponse<String> response) {
        try { return mapper.readTree(response.body()); }
        catch (JsonProcessingException e) { throw new RuntimeException(e); }
    }

    private HttpResponse<String> getHttpResponse(HttpRequest request) {
        try { return client.send(request, HttpResponse.BodyHandlers.ofString()); }
        catch (Exception e) { throw new RuntimeException(e); }
    }

    private HttpRequest getHttpRequest(String url, String operation){
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method(operation, HttpRequest.BodyPublishers.noBody())
                .build();
    }

    private Person getPerson(JsonNode node) {
        return new Person(
                null,
                getString(node.findValue("firstName")),
                getString(node.findValue("lastName")),
                getString(node.findValue("email")),
                getString(node.findValue("workEmail")),
                getString(node.findValue("mobilePhone")),
                getString(node.findValue("workPhone")),
                getDate(node.findValue("employmentStartDate")),
                getDate(node.findValue("contractEndDate")),
                null,
                getEnum(node.findValue("accessType"), AccessType.class),
                getDate(node.findValue("birthday")),
                getBloodType(node.findValue("bloodType")),
                getEnum(node.findValue("educationalLevel"), EducationalLevel.class),
                getEnum(node.findValue("educationStatus"), EducationStatus.class),
                getString(node.findValue("educationalInstitution")),
                getEnum(node.findValue("gender"), Gender.class),
                getString(node.findValue("idNumber")),
                getEnum(node.findValue("levelOfDisability"), LevelOfDisability.class),
                getEnum(node.findValue("maritalStatus"), MaritalStatus.class),
                getBoolean(node.findValue("partnerWorkStatus")),
                getEnum(node.findValue("militaryService"), MilitaryService.class),
                getDate(node.findValue("militaryServiceDelayDate")),
                getString(node.findValue("nationality")),
                getInteger(node.findValue("numberOfChildren")),
                getEnum(node.findValue("tourStatus"), TourStatus.class)
        );
    }

    private List<Person> getPeople(JsonNode nodes) {
        List<Person> people = new ArrayList<>();
        for (JsonNode node: nodes) people.add(getPerson(node));
        return people;
    }

    private String getString(JsonNode node) {
        return isNull(node)? null : node.asText();
    }

    private Integer getInteger(JsonNode node) {
        return isNull(node)? null : Integer.getInteger(node.asText());
    }

    private Boolean getBoolean(JsonNode node) {
        return isNull(node)? null : node.asBoolean();
    }

    private <T extends Enum<T>> T getEnum(JsonNode node, Class<T> type) {
        return isNull(node)? null : T.valueOf(type, capitalize(node.asText()));
    }

    private LocalDate getDate(JsonNode node) {
        String s = getString(node);
        return (s == null)? null : LocalDate.parse(s);
    }

    private BloodType getBloodType(JsonNode node) {
        String s = getString(node);
        return (s == null)? null : BloodType.valueOf(s.replace("-", "Negative").replace("+", "Positive"));
    }

    private boolean isNull(JsonNode node) {
        return (node == null || node.asText().equals("null"));
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
