package com.sadesoft.portal.repo.kolay;

import com.sadesoft.portal.base.rest.MockRestResponder;
import com.sadesoft.portal.base.rest.RestRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class KolayPersonClientTest {
    private List<KolayPerson> people;
    private MockPersonResponder responder;
    private KolayPersonClient client;
    private KolayPerson person;

    @BeforeEach
    public void setUp() {
        people = new ArrayList<>();
        responder = new MockPersonResponder();
        client = new KolayPersonClient(responder);
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
            assertEquals(people, client.viewBulkPerson(people.stream().map(p -> p.id).toList()));
            assertEquals(1, responder.getRequestCount());
            assertEquals(responder.getInteraction(0).request, responder.getRequest(0));
        }
    }

    private static class MockPersonResponder extends MockRestResponder {
        public void saveListResponse(List<KolayPerson> people) {
            save(new RestRequest("/person/list", "POST", ""), createPeopleResponseJson(people, "items"));
        }

        public void saveViewResponse(KolayPerson p) {
            save(new RestRequest("/person/view/" + p.id, "GET", ""), createPersonResponseJson(p));
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

        private String createPeopleJson(List<KolayPerson> people, String title) {
            return "{ \"" + title + "\":["
                    + String.join(",", people.stream().map(p -> p.toJson().toString()).toList())
                    + "]}";
        }

        private String createPeopleResponseJson(List<KolayPerson> people, String title) {
            return createResponseJson(createPeopleJson(people, title));
        }

        private String createPersonJson(KolayPerson person) {
            return "{ \"person\":" + person.toJson() + " }";
        }

        private String createPersonResponseJson(KolayPerson person) {
            return createResponseJson(createPersonJson(person));
        }

        private String createResponseJson(String data) {
            return "{ \"error\": false," + " \"data\":" + data + "}";
        }

        private String createIdListJson(List<KolayPerson> people) {
            return "\"person_ids\" : [" + String.join(",", people.stream().map(p -> p.id).toList()) + "]";
        }
    }
}
