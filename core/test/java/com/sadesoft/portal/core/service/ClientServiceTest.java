package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.query.ClientQuery;
import com.sadesoft.portal.core.mock.MockClientRepo;
import com.sadesoft.portal.core.mock.MockSectorRepo;
import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.model.Sector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientServiceTest {
    private MockClientRepo clientRepo;
    private MockSectorRepo sectorRepo;
    private ClientService service;
    private Client validClient;

    @BeforeEach
    public void setup() {
        sectorRepo = new MockSectorRepo();
        clientRepo = new MockClientRepo(sectorRepo);
        service = new ClientService(clientRepo);
        validClient = new Client(null, "0", "Sample Firm");
    }

    @Test
    public void createsClient() {
        Client client = service.create(validClient);
        assertEquals(validClient.withId("0"), client);
        assertEquals(1, clientRepo.list().size());
        assertEquals(client, clientRepo.read("0"));
    }

    @Nested
    public class GivenServiceWithOneClient {
        @BeforeEach
        public void setup() {
            clientRepo.create(validClient);
        }

        @Test
        public void listsClients() {
            assertEquals(1, service.listBy(new ClientQuery()).size());
            assertEquals(validClient.withId("0"), service.listBy(new ClientQuery()).get(0));
        }

        @Test
        public void listsByOffset() {
            clientRepo.create(validClient.copy());
            assertEquals(2, service.listBy(new ClientQuery().withOffset(0)).size());
            assertEquals(1, service.listBy(new ClientQuery().withOffset(1)).size());
            assertEquals(0, service.listBy(new ClientQuery().withOffset(2)).size());
        }

        @Test
        public void listsDetailedClients() {
            sectorRepo.create(new Sector(null, "Sample"));
            assertEquals(clientRepo.listDetailedBy(new ClientQuery()), service.listDetailedBy(new ClientQuery()));
        }

        @Test
        public void listSummarizedClients() {
            assertEquals(1, service.listSummarized().size());
            assertEquals(null, service.listSummarized().get(0).sectorId);
        }

        @Test
        public void readsClients() {
            assertEquals(validClient.withId("0"), service.read("0"));
        }

        @Test
        public void readsDetailedClient() {
            sectorRepo.create(new Sector(null, "Sample"));
            assertEquals(clientRepo.readDetailed("0"), service.readDetailed("0"));
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
            assertEquals(0, service.listBy(new ClientQuery()).size());
        }
    }
}
