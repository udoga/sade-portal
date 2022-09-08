package com.sadesoft.portal.repo.kolay;

import com.fasterxml.jackson.databind.JsonNode;
import com.sadesoft.portal.base.rest.JsonMapper;
import com.sadesoft.portal.base.rest.MockRestResponder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KolayPersonResponder extends MockRestResponder {
    private final JsonMapper mapper;

    public KolayPersonResponder(JsonMapper mapper) {
        super("/person");
        this.mapper = mapper;
    }

    public void saveListResponse(List<KolayPerson> people) {
        save("/list", "POST", null, createPeopleResponseJson(people, "items"));
    }

    public void saveViewResponse(KolayPerson p) {
        save("/view/" + p.id, "GET", null, createPersonResponseJson(p));
    }

    public void saveCreateResponse(KolayPerson p) {
        save("/create", "POST", createPersonJson(p.withId(null)), createPersonResponseJson(p));
    }

    public void saveUpdateResponse(KolayPerson p) {
        save("/update", "PUT", createPersonJson(p.withUpdatedAt(null)), createPersonResponseJson(p));
    }

    public void saveBulkResponse(List<KolayPerson> people) {
        save("/bulk-view", "POST", createIdListJson(people), createPeopleResponseJson(people, "persons"));
    }

    public void saveTerminateResponse(KolayPerson p, KolayTermination t) {
        save("/terminate", "POST", mapper.objectToJson(t), createPersonResponseJson(p));
    }

    private JsonNode createPersonJson(KolayPerson person) {
        return mapper.objectToJson(Map.of("person", person));
    }

    private JsonNode createPersonResponseJson(KolayPerson person) {
        return createResponseJson(createPersonJson(person));
    }

    private JsonNode createPeopleResponseJson(List<KolayPerson> people, String title) {
        return createResponseJson(mapper.objectToJson(Map.of(title, people)));
    }

    private JsonNode createResponseJson(JsonNode data) {
        return mapper.objectToJson(Map.of("error", false, "data", data));
    }

    private JsonNode createIdListJson(List<KolayPerson> people) {
        return mapper.objectToJson(Map.of("person_ids", people.stream().map(p -> p.id).collect(Collectors.toList())));
    }
}
