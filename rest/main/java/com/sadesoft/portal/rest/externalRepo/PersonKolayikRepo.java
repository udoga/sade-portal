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
    private final String serviceUrl = "http://localhost:8080/mock/person/";

    @Override
    public List<Person> list() {
        return getPersonList(fetchNode(serviceUrl + "list", "POST", "items"));
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
        return getPerson(fetchNode(serviceUrl + "view/" + id, "GET", "person"));
    }

    @Override
    public Person update(int id, Person record) {
        return record;
    }

    @Override
    public Person delete(int id) {
        return null;
    }

    private JsonNode fetchNode(String url, String method, String nodeName) {
        HttpRequest request = getHttpRequest(url, method);
        HttpResponse<String> httpResponse = getHttpResponse(request);
        JsonNode rootNode = getRootNode(httpResponse);
        JsonNode dataNode = getDataNode(rootNode);
        return dataNode.findValue(nodeName);
    }

    private JsonNode getDataNode(JsonNode rootNode) {
        return rootNode.findValue("data");
    }

    private JsonNode getRootNode(HttpResponse<String> response) {
        try {
            return mapper.readTree(response.body());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpResponse<String> getHttpResponse(HttpRequest request) {
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private HttpRequest getHttpRequest(String url, String operation){
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method(operation, HttpRequest.BodyPublishers.noBody())
                .build();
    }

    private Person getPerson(JsonNode node) {
        return new Person()
                .withFirstName(getString(node.findValue("firstName")))
                .withLastName(getString(node.findValue("lastName")))
                .withEmail(getString(node.findValue("email")))
                .withWorkEmail(getString(node.findValue("workEmail")))
                .withMobilePhone(getString(node.findValue("mobilePhone")))
                .withWorkPhone(getString(node.findValue("workPhone")))
                .withEmploymentStartDate(getDate(node.findValue("employmentStartDate")))
                .withContractEndDate(getDate(node.findValue("contractEndDate")))
                .withAccessType(getEnum(node.findValue("accessType"), AccessType.class))
                .withBirthday(getDate(node.findValue("birthday")))
                .withBloodType(getBloodType(node.findValue("bloodType")))
                .withEducationalLevel(getEnum(node.findValue("educationalLevel"), EducationalLevel.class))
                .withEducationStatus(getEnum(node.findValue("educationStatus"), EducationStatus.class))
                .withEducationalInstitution(getString(node.findValue("educationalInstitution")))
                .withGender(getEnum(node.findValue("gender"), Gender.class))
                .withIdNumber(getString(node.findValue("idNumber")))
                .withLevelOfDisability(getEnum(node.findValue("levelOfDisability"), LevelOfDisability.class))
                .withMaritalStatus(getEnum(node.findValue("maritalStatus"), MaritalStatus.class))
                .withPartnerWorkStatus(getBoolean(node.findValue("partnerWorkStatus")))
                .withMilitaryService(getEnum(node.findValue("militaryService"), MilitaryService.class))
                .withMilitaryServiceDelayDate(getDate(node.findValue("militaryServiceDelayDate")))
                .withNationality(getString(node.findValue("nationality")))
                .withNumberOfChildren(getInteger(node.findValue("numberOfChildren")))
                .withTourStatus(getEnum(node.findValue("tourStatus"), TourStatus.class));
    }

    private List<Person> getPersonList(JsonNode itemNodes) {
        List<Person> people = new ArrayList<>();
        for (JsonNode node: itemNodes)
            people.add(getPerson(node));
        return people;
    }

    private String getString(JsonNode node) {
        return (isNull(node))? null : node.asText();
    }

    private LocalDate getDate(JsonNode node) {
        return (isNull(node))? null : LocalDate.parse(getString(node));
    }

    public <T extends Enum<T>> T getEnum(JsonNode node, Class<T> type) {
        return (isNull(node))? null : T.valueOf(type, capitalize(node.asText()));
    }

    private BloodType getBloodType(JsonNode node) {
        return (isNull(node)) ? null : BloodType.valueOf(convertBloodType(getString(node)));
    }

    private Boolean getBoolean(JsonNode node) {
        return (isNull(node))? null : node.asBoolean();
    }

    private static boolean isNull(JsonNode node) {
        return (node == null || node.asText().equals("null"));
    }

    private Integer getInteger(JsonNode node) {
        return (isNull(node))? null : Integer.getInteger(node.asText());
    }

    public static String capitalize(String str) {
        if (str == null || str.isEmpty())
            return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private String convertBloodType(String str) {
        if (str.contains("-")) return str.replace("-", "Negative");
        return str.replace("+", "Positive");
    }
}
