package com.sadesoft.portal.repo.kolay;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadesoft.portal.base.rest.RestRequest;
import com.sadesoft.portal.base.rest.RestResponder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KolayPersonClientTest {
    private List<KolayPerson> people;
    private MockRestResponder responder;
    private KolayPersonClient client;
    private KolayPerson person;

    @BeforeEach
    public void setUp() {
        people = new ArrayList<>();
        responder = new MockRestResponder();
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
        responder.saveCreateResponse(person.withId("0"));
        assertEquals(person.withId("0"), client.createPerson(person));
        assertEquals(1, responder.requests.size());
        assertEquals(responder.interactions.get(0).request, responder.requests.get(0));
    }

    @Nested
    public class GivenResponderWithTwoPeople {
        @BeforeEach
        void setUp() {
            people.add(new KolayPerson().withId("0").withFirstName("Emma").withLastName("Doe"));
            people.add(new KolayPerson().withId("1").withFirstName("John").withLastName("Ipsum"));
            responder.saveListResponse(people);
            responder.saveViewResponse(people.get(0));
            responder.saveViewResponse(people.get(1));
        }

        @Test
        void fetchesPeopleList() {
            assertEquals(people, client.listPeople());
        }

        @Test
        void viewsPersonById() {
            assertEquals(people.get(0), client.viewPerson(people.get(0).id));
            assertEquals(people.get(1), client.viewPerson(people.get(1).id));
        }
    }

    private static class MockRestResponder implements RestResponder {
        private final List<Interaction> interactions = new ArrayList<>();
        private final List<RestRequest> requests = new ArrayList<>();

        @Override
        public JsonNode respond(RestRequest r) {
            requests.add(r);
            for (Interaction interaction : interactions)
                if (Objects.equals(r, interaction.request))
                    return interaction.response;
            return null;
        }

        public void saveListResponse(List<KolayPerson> people) {
            save(new RestRequest("/person/list", "POST", ""), createPeopleResponseJson(people));
        }

        public void saveViewResponse(KolayPerson p) {
            save(new RestRequest("/person/view/" + p.id, "GET", ""), createPersonResponseJson(p));
        }

        public void saveCreateResponse(KolayPerson p) {
            save(new RestRequest("/person/create", "POST", createPersonJson(p.withId(null))), createPersonResponseJson(p));
        }

        private void save(RestRequest r, String response) {
            interactions.add(new Interaction(r, response));
        }

        private String createPeopleJson(List<KolayPerson> people) {
            return String.join(",", people.stream().map(p -> p.toJson().toString()).toList());
        }

        private String createPeopleResponseJson(List<KolayPerson> people) {
            return "{\n" +
                    "  \"error\": false,\n" +
                    "  \"data\": {\n" +
                    "    \"total\": 2,\n" +
                    "    \"perPage\": 60,\n" +
                    "    \"currentPage\": 1,\n" +
                    "    \"lastPage\": 1,\n" +
                    "    \"items\": [" + createPeopleJson(people) + "]\n" +
                    "  }\n" +
                    "}";
        }

        private String createPersonResponseJson(KolayPerson person) {
            return "{ \"error\": false," + " \"data\":" + createPersonJson(person) + "}";
        }

        private String createPersonJson(KolayPerson person) {
            return "{ \"person\":" + person.toJson() + " }";
        }
    }

    private static class Interaction {
        public final RestRequest request;
        public final JsonNode response;

        Interaction(RestRequest r, String response) {
            this.request = r;
            this.response = toJsonNode(response);
        }

        private JsonNode toJsonNode(String response) {
            try {
                return new ObjectMapper().readTree(response);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
