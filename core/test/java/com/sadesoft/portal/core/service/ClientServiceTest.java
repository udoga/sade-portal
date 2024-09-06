package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.query.ClientQuery;
import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.mock.MockClientRepo;
import com.sadesoft.portal.core.mock.MockSectorRepo;
import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.model.Sector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingFieldOnCreate(validClient.withSectorId(null));
        assertMissingFieldOnCreate(validClient.withName(null));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() {
        assertInvalidInputOnCreate(validClient.withSectorId("A"));
        assertInvalidInputOnCreate(validClient.withSectorId(""));
        assertInvalidInputOnCreate(validClient.withName("1"));
        assertInvalidInputOnCreate(validClient.withName(""));
        assertDoesNotThrow(() -> service.create(validClient.withSectorId("11")));
        assertDoesNotThrow(() -> service.create(validClient.withName("A B")));
    }

    private void assertMissingFieldOnCreate(Client client) {
        assertThrows(MissingFieldError.class, () -> service.create(client));
    }

    private void assertInvalidInputOnCreate(Client client) {
        assertThrows(InvalidInputError.class, () -> service.create(client));
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
        public void listsClientOptions() {
            assertEquals(1, service.listOptions().size());
            assertNull(service.listOptions().get(0).sectorId);
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

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError() {
            assertMissingFieldOnUpdate(validClient.withSectorId(null));
            assertMissingFieldOnUpdate(validClient.withName(null));
        }

        @Test
        public void whenHasInvalidInputOnUpdate_throwsError() {
            assertInvalidInputOnUpdate(validClient.withSectorId("A"));
            assertInvalidInputOnUpdate(validClient.withSectorId(""));
            assertInvalidInputOnUpdate(validClient.withName("1"));
            assertInvalidInputOnUpdate(validClient.withName(""));
            assertDoesNotThrow(() -> service.update("0", validClient.withSectorId("11")));
            assertDoesNotThrow(() -> service.update("0", validClient.withName("A B")));
        }

        private void assertMissingFieldOnUpdate(Client client) {
            assertThrows(MissingFieldError.class, () -> service.update("0", client));
        }

        private void assertInvalidInputOnUpdate(Client client) {
            assertThrows(InvalidInputError.class, () -> service.update("0", client));
        }
    }
}
