package com.sadesoft.portal.repo.kolay;

import com.fasterxml.jackson.databind.JsonNode;
import com.sadesoft.portal.base.rest.JsonMapper;
import com.sadesoft.portal.base.rest.RestRequest;
import com.sadesoft.portal.base.rest.RestResponder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class KolayPersonClient {
    private final RestResponder responder;
    private final JsonMapper mapper;

    public KolayPersonClient(RestResponder responder, JsonMapper mapper) {
        this.responder = responder;
        this.mapper = mapper;
    }

    public List<KolayPerson> listPeople() {
        return getKolayPeople(getResponse("/person/list", "POST", null), "items");
    }

    public KolayPerson createPerson(KolayPerson person) {
        return getKolayPerson(getResponse("/person/create", "POST", createPersonJson(person)));
    }

    public KolayPerson viewPerson(String id) {
        return getKolayPerson(getResponse("/person/view/" + id, "GET", null));
    }

    public KolayPerson updatePerson(KolayPerson person) {
        return getKolayPerson(getResponse("/person/update" , "PUT", createPersonJson(person)));
    }

    public List<KolayPerson> viewBulkPerson(List<String> ids) {
        return getKolayPeople(getResponse("/person/bulk-view" , "POST", createIdListJson(ids)), "persons");
    }

    public KolayPerson terminateEmployee(KolayTermination termination) {
        return getKolayPerson(getResponse("/person/terminate" , "POST", createTerminationJson(termination)));
    }

    private JsonNode createPersonJson(KolayPerson person) {
        return mapper.toJson(Map.of("person", person));
    }

    private JsonNode createTerminationJson(KolayTermination termination) {
        return mapper.toJson(termination);
    }

    private JsonNode createIdListJson(List<String> ids) {
        return mapper.toJson(Map.of("person_ids", ids));
    }

    private KolayPerson getKolayPerson(JsonNode response) {
        return mapper.toObject(response.findValue("data").findValue("person"), KolayPerson.class);
    }

    private List<KolayPerson> getKolayPeople(JsonNode response, String title) {
        return Arrays.asList(mapper.toObject(response.findValue("data").findValue(title), KolayPerson[].class));
    }

    private JsonNode getResponse(String url, String method, JsonNode body) {
        return responder.respond(new RestRequest(url, method, body));
    }
}
