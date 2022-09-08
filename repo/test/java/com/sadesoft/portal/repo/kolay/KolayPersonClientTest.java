package com.sadesoft.portal.repo.kolay;

import com.fasterxml.jackson.databind.JsonNode;
import com.sadesoft.portal.base.rest.JsonMapper;
import com.sadesoft.portal.base.rest.MockRestResponder;
import com.sadesoft.portal.base.rest.RestRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KolayPersonClientTest {
    private List<KolayPerson> people;
    private JsonMapper mapper;
    private MockPersonResponder responder;
    private KolayPersonClient client;
    private KolayPerson person;

    @BeforeEach
    public void setUp() {
        people = new ArrayList<>();
        mapper = new JsonMapper();
        responder = new MockPersonResponder(mapper);
        client = new KolayPersonClient(responder, mapper);
        person = new KolayPerson().withFirstName("Sample");
    }

    @Test
    void whenThereIsOnePerson_fetchesPeopleList() {
        people.add(person);
        responder.saveListResponse(people);
        assertEquals(people, client.listPeople());
    }

    @Test
    void whenCreatesPerson_returnsResponseFromResponder() {
        KolayPerson createdPerson = person.withId("0");
        responder.saveCreateResponse(createdPerson);
        assertEquals(createdPerson, client.createPerson(person));
        assertEquals(1, responder.getRequestCount());
        assertEquals(responder.getInteraction(0).request, responder.getRequest(0));
    }

    @Test
    void whenUpdatesPerson_sendsUpdateRequest() {
        KolayPerson updatedPerson = person.withId("0").withUpdatedAt("2022-01-01");
        responder.saveUpdateResponse(updatedPerson);
        assertEquals(updatedPerson, client.updatePerson(person.withId("0")));
        assertEquals(1, responder.getRequestCount());
        assertEquals(responder.getInteraction(0).request, responder.getRequest(0));
    }

    @Test
    void whenUpdatesPerson_returnsResponseFromResponder() {
        KolayPerson updatedPerson = person.withId("0").withUpdatedAt("2022-02-02");
        responder.saveUpdateResponse(updatedPerson);
        assertEquals(updatedPerson, client.updatePerson(person.withId("0")));
    }

    @Test
    void terminatesEmployee() {
        KolayPerson terminatedPerson = person.withStatus("terminated");
        KolayTermination termination = new KolayTermination("0" ,"2022-02-02", "5", "details");
        responder.saveTerminateResponse(terminatedPerson, termination);
        assertEquals(terminatedPerson, client.terminateEmployee(termination));
    }

    @Nested
    public class GivenResponderWithTwoPeople {
        @BeforeEach
        void setUp() {
            people.add(new KolayPerson().withId("0").withFirstName("Emma").withLastName("Doe"));
            people.add(new KolayPerson().withId("1").withFirstName("John").withLastName("Ipsum"));
        }

        @Test
        void fetchesPeopleList() {
            responder.saveListResponse(people);
            assertEquals(people, client.listPeople());
        }

        @Test
        void viewsPersonById() {
            responder.saveViewResponse(people.get(0));
            responder.saveViewResponse(people.get(1));
            assertEquals(people.get(0), client.viewPerson(people.get(0).id));
            assertEquals(people.get(1), client.viewPerson(people.get(1).id));
        }

        @Test
        void viewsBulkPerson() {
            responder.saveBulkResponse(people);
            assertEquals(people, client.viewBulkPerson(people.stream().map(p -> p.id).collect(Collectors.toList())));
            assertEquals(1, responder.getRequestCount());
            assertEquals(responder.getInteraction(0).request, responder.getRequest(0));
        }
    }

    private static class MockPersonResponder extends MockRestResponder {
        private final JsonMapper mapper;

        public MockPersonResponder(JsonMapper mapper) {
            this.mapper = mapper;
        }

        public void saveListResponse(List<KolayPerson> people) {
            save(new RestRequest("/person/list", "POST", null), createPeopleResponseJson(people, "items"));
        }

        public void saveViewResponse(KolayPerson p) {
            save(new RestRequest("/person/view/" + p.id, "GET", null), createPersonResponseJson(p));
        }

        public void saveCreateResponse(KolayPerson p) {
            save(new RestRequest("/person/create", "POST", createPersonJson(p.withId(null))), createPersonResponseJson(p));
        }

        public void saveUpdateResponse(KolayPerson p) {
            save(new RestRequest("/person/update", "PUT", createPersonJson(p.withUpdatedAt(null))), createPersonResponseJson(p));
        }

        public void saveBulkResponse(List<KolayPerson> people) {
            save(new RestRequest("/person/bulk-view", "POST", createIdListJson(people)), createPeopleResponseJson(people, "persons"));
        }

        public void saveTerminateResponse(KolayPerson p, KolayTermination t) {
            save(new RestRequest("/person/terminate", "POST", createTerminationJson(t)), createPersonResponseJson(p));
        }

        private JsonNode createTerminationJson(KolayTermination termination) {
           return mapper.objectToJson(termination);
        }

        private JsonNode createPeopleJson(List<KolayPerson> people, String title) {
            return mapper.objectToJson(Map.of(title, people));
        }

        private JsonNode createPeopleResponseJson(List<KolayPerson> people, String title) {
            return createResponseJson(createPeopleJson(people, title));
        }

        private JsonNode createPersonJson(KolayPerson person) {
            return mapper.objectToJson(Map.of("person", person));
        }

        private JsonNode createPersonResponseJson(KolayPerson person) {
            return createResponseJson(createPersonJson(person));
        }

        private JsonNode createResponseJson(JsonNode data) {
            return mapper.objectToJson(Map.of("error", false, "data", data));
        }

        private JsonNode createIdListJson(List<KolayPerson> people) {
            return mapper.objectToJson(Map.of("person_ids", people.stream().map(p -> p.id).collect(Collectors.toList())));
        }
    }
}
