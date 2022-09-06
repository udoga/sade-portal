package com.sadesoft.portal.repo.kolay;

import com.fasterxml.jackson.databind.JsonNode;
import com.sadesoft.portal.base.rest.RestClient;
import com.sadesoft.portal.base.error.ClientError;
import com.sadesoft.portal.base.rest.RestRequest;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.repo.PersonRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KolayPersonRepo implements PersonRepo {
    private final RestClient client;

    public KolayPersonRepo(KolayConfig config) {
        this.client = new RestClient(config.url + "/person");
    }

    @Override
    public List<Person> list() {
        return getPeople(client.respond(new RestRequest("/list", "POST", null)).findValue("data").findValue("items"));
    }

    @Override
    public List<Person> listPage(int pageNo, int pageSize) {
        return list();
    }

    @Override
    public Person create(Person record) {
        return getPerson(client.respond(new RestRequest("/create/", "POST", toJson(record))).findValue("data").findValue("person"));
    }

    @Override
    public Person read(String id) {
        return getPerson(client.respond(new RestRequest("/view/" + id, "GET", null)).findValue("data").findValue("person"));
    }

    @Override
    public Person update(String id, Person record) {
        return getPerson(client.respond(new RestRequest("/update/", "PUT", toJson(record))).findValue("data").findValue("person"));
    }

    @Override
    public Person delete(String id)  {
        throw new ClientError("KolayIK API doesn't support delete operation.");
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
                getBoolean(node.findValue("active")),
                getEnum(node.findValue("tourStatus"), TourStatus.class)
        );
    }

    private String toJson(Person person) {
        return "";
    }

    private Boolean isGraduated(JsonNode node) {
        String str = getString(node);
        if (Objects.equals(str, "graduate")) return true;
        if (Objects.equals(str, "student"))  return false;
        return null;
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
