package com.sadesoft.portal.rest.kolay;

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
import java.util.Objects;

public class PersonKolayRepo implements Repo<Person> {
    private final ObjectMapper mapper;
    private final HttpClient client;
    private final String serviceUrl;

    public PersonKolayRepo(KolayConfig config) {
        this.mapper = new ObjectMapper();
        this.client = HttpClient.newHttpClient();
        this.serviceUrl = config.url + "/person";
    }

    @Override
    public List<Person> list() {
        return getPeople(fetchNode(serviceUrl + "/list", "POST", "items"));
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
    public Person read(String id) {
        return getPerson(fetchNode(serviceUrl + "/view/" + id, "GET", "person"));
    }

    @Override
    public Person update(String id, Person record) {
        return null;
    }

    @Override
    public Person delete(String id) {
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

    private HttpRequest getHttpRequest(String url, String operation) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method(operation, HttpRequest.BodyPublishers.noBody())
                .build();
    }

    private Person getPerson(JsonNode node) {
        return new Person(
                getString(node.findValue("id")),
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
                getEnum(node.findValue("educationLevel"), EducationLevel.class),
                isGraduated(node.findValue("educationStatus")),
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

    private Boolean isGraduated(JsonNode educationStatus) {
        return Objects.equals(getString(educationStatus), "Graduate");
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
        return isNull(node)? null : node.asInt();
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
        if (s == null) return null;
        return BloodType.valueOf(s.replace("0", "Zero").replace("-", "Negative").replace("+", "Positive"));
    }

    private boolean isNull(JsonNode node) {
        return (node == null || node.isNull());
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
