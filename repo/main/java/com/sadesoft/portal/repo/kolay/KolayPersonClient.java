package com.sadesoft.portal.repo.kolay;

import com.fasterxml.jackson.databind.JsonNode;
import com.sadesoft.portal.base.rest.RestRequest;
import com.sadesoft.portal.base.rest.RestResponder;

import java.util.ArrayList;
import java.util.List;

public class KolayPersonClient {
    private final RestResponder responder;

    public KolayPersonClient(RestResponder responder) {
        this.responder = responder;
    }

    public List<KolayPerson> listPeople() {
        JsonNode node = responder.respond(new RestRequest("/person/list", "POST", ""));
        return getKolayPeople(node.findValue("data").findValue("items"));
    }

    public KolayPerson createPerson(KolayPerson person) {
        JsonNode node = responder.respond(new RestRequest("/person/create" , "POST", createPersonJson(person)));
        return KolayPerson.fromJson(node.findValue("data").findValue("person"));
    }

    public KolayPerson viewPerson(String id) {
        JsonNode node = responder.respond(new RestRequest("/person/view/" + id, "GET", ""));
        return KolayPerson.fromJson(node.findValue("data").findValue("person"));
    }

    private String createPersonJson(KolayPerson person) {
        return "{ \"person\":" + person.toJson()+ " }";
    }

    private List<KolayPerson> getKolayPeople(JsonNode nodes) {
        List<KolayPerson> people = new ArrayList<>();
        for (JsonNode node: nodes) people.add(KolayPerson.fromJson(node));
        return people;
    }
}
