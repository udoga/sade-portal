package com.sadesoft.portal.repo.kolay;

import com.sadesoft.portal.base.rest.JsonMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KolayPersonClientTest {
    private JsonMapper mapper;
    private KolayPersonResponder responder;
    private KolayPersonClient client;
    private KolayPerson person;
    private List<KolayPerson> people;

    @BeforeEach
    public void setUp() {
        mapper = new JsonMapper();
        responder = new KolayPersonResponder(mapper);
        client = new KolayPersonClient(responder, mapper);
        person = new KolayPerson().withFirstName("Sample");
        people = new ArrayList<>();
    }

    @Test
    void whenThereIsOnePerson_fetchesPeopleList() {
        people.add(person);
        responder.saveListResponse(people);
        assertEquals(people, client.listPeople());
    }

    @Test
    void whenCreatesPerson_sendsCreateRequest() {
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
        KolayTermination termination = new KolayTermination("0", "2022-02-02", "5", "details");
        responder.saveTerminateResponse(terminatedPerson, termination);
        assertEquals(terminatedPerson, client.terminateEmployee(termination));
        assertEquals(1, responder.getRequestCount());
        assertEquals(responder.getInteraction(0).request, responder.getRequest(0));
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
}
