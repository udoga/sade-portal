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
        ObjectMapper mapper = new ObjectMapper();

        @Override
        public JsonNode respond(String url, String method, String body) {
            String jsonString = """
                    {
                      "error": false,
                      "data": {
                        "total": 2,
                        "perPage": 60,
                        "currentPage": 1,
                        "lastPage": 1,
                        "items": [
                          {
                            "id": "0",
                            "firstName": "John",
                            "lastName": "Doe"
                          },
                          {
                            "id": "1",
                            "firstName": "John",
                            "lastName": "Ipsum"
                          }
                        ]
                      }
                    }""";

            try { return mapper.readTree(jsonString); }
            catch (JsonProcessingException e) { throw new RuntimeException(e); }
        }
    }
}
