package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockClientRepo;
import com.sadesoft.portal.core.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientServiceTest {
    private MockClientRepo repo;
    private ClientService service;
    private Client validClient;

    @BeforeEach
    public void setup() {
        repo = new MockClientRepo();
        service = new ClientService(repo);
        validClient = new Client(null, "Sample Firm");
    }

    @Test
    public void createsClient() {
        Client client = service.create(validClient);
        assertEquals(validClient.withId("0"), client);
        assertEquals(1, repo.list().size());
        assertEquals(client, repo.read("0"));
    }

    @Nested
    public class GivenServiceWithOneClient {
        @BeforeEach
        public void setup() {
            repo.create(validClient);
        }

        @Test
        public void listsClients() {
            assertEquals(1, service.list().size());
            assertEquals(validClient.withId("0"), service.list().get(0));
        }

        @Test
        public void readsClients() {
            assertEquals(validClient.withId("0"), service.read("0"));
        }

        @Test
        public void updatesClientById() {
            Client client = service.update("0", validClient.withName("updated"));
            assertEquals(validClient.withId("0").withName("updated"), client);
            assertEquals(client, service.read("0"));
        }

        @Test
        public void deletesClientById() {
            Client client = service.delete("0");
            assertEquals(validClient.withId("0"), client);
            assertEquals(0, service.list().size());
        }
    }
}
