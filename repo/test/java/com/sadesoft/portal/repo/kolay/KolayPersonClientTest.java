package com.sadesoft.portal.repo.kolay;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private String createPeopleJson(List<KolayPerson> people) {
        return String.join(",", people.stream().map(p -> p.toJson().toString()).toList());
    }

    private String createListResponseJson(List<KolayPerson> people) {
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

    private String createViewResponseJson(KolayPerson person){
        return "{ \"error\": false," + " \"data\": { \"person\":" + person.toJson()+ " }}";
    }

    @BeforeEach
    public void setUp() {
        people = new ArrayList<>();
        responder = new MockRestResponder();
        client = new KolayPersonClient(responder);
    }

    @Test
    void whenThereIsOnePerson_fetchesPeopleList() {
        people.add(new KolayPerson().withFirstName("Sample"));
        responder.add("/person/list", "POST", "", createListResponseJson(people));
        assertEquals(people, client.listPeople());
    }

    @Nested
    public class GivenResponderWithTwoPeople {
        @BeforeEach
        void setUp() {
            people.add(new KolayPerson().withId("0").withFirstName("Emma").withLastName("Doe"));
            people.add(new KolayPerson().withId("1").withFirstName("John").withLastName("Ipsum"));
            responder.add("/person/list", "POST", "", createListResponseJson(people));
            responder.add("/person/view/" + people.get(0).id, "GET", "", createViewResponseJson(people.get(0)));
            responder.add("/person/view/" + people.get(1).id, "GET", "", createViewResponseJson(people.get(1)));
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
        private final List<RequestResponsePair> pairs = new ArrayList<>();

        public void add(String url, String method, String body, String response) {
            pairs.add(new RequestResponsePair(url, method, body, response));
        }

        @Override
        public JsonNode respond(String url, String method, String body) {
            for (RequestResponsePair pair : pairs)
                if (pair.hasRequest(url, method, body))
                    return pair.response;
            return null;
        }
    }

    private static class RequestResponsePair {
        public final String url;
        public final String method;
        public final String body;
        public final JsonNode response;

        RequestResponsePair(String url, String method, String body, String response) {
            this.url = url;
            this.method = method;
            this.body = body;
            this.response = toJsonNode(response);
        }

        public boolean hasRequest(String url, String method, String body) {
            return  Objects.equals(url, this.url) &&
                    Objects.equals(method, this.method) &&
                    Objects.equals(body, this.body);
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
