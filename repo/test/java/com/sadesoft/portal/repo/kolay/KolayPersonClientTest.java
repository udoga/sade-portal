package com.sadesoft.portal.repo.kolay;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadesoft.portal.base.rest.RestResponder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class KolayPersonClientTest {
    private MockRestResponder responder;
    private KolayPersonClient client;

    @BeforeEach
    void setUp() {
        responder = new MockRestResponder();
        client= new KolayPersonClient(responder);
    }

    @Test
    void fetchesPeopleList() {
        List<KolayPerson> people = client.listPeople();
        assertEquals(2, people.size());
        assertEquals(new KolayPerson().withId("0").withFirstName("John").withLastName("Doe"), people.get(0));
        assertEquals(new KolayPerson().withId("1").withFirstName("John").withLastName("Ipsum"), people.get(1));
    }

    private static class MockRestResponder implements RestResponder {
        private final ObjectMapper mapper = new ObjectMapper();

        @Override
        public JsonNode respond(String url, String method, String body) {
            String jsonString = "{\n" +
                    "  \"error\": false,\n" +
                    "  \"data\": {\n" +
                    "    \"total\": 2,\n" +
                    "    \"perPage\": 60,\n" +
                    "    \"currentPage\": 1,\n" +
                    "    \"lastPage\": 1,\n" +
                    "    \"items\": [\n" +
                    "      {\n" +
                    "        \"id\": \"0\",\n" +
                    "        \"firstName\": \"John\",\n" +
                    "        \"lastName\": \"Doe\"\n" +
                    "      },\n" +
                    "      {\n" +
                    "        \"id\": \"1\",\n" +
                    "        \"firstName\": \"John\",\n" +
                    "        \"lastName\": \"Ipsum\"\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}";

            try { return mapper.readTree(jsonString); }
            catch (JsonProcessingException e) { throw new RuntimeException(e); }
        }
    }
}
