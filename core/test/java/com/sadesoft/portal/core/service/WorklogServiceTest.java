package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockWorklogRepo;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.sample.WorklogSamples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorklogServiceTest {
    private MockWorklogRepo repo;
    private WorklogService service;
    private Worklog validWorklog;

    @BeforeEach
    public void setup() {
        repo = new MockWorklogRepo();
        service = new WorklogService(repo);
        validWorklog = WorklogSamples.validWorklog.copy();
    }

    @Nested
    public class GivenServiceWithOneClient {
        @BeforeEach
        public void setup() {
            repo.create(validWorklog);
        }

        @Test
        public void listsClients() {
            assertEquals(1, service.list().size());
            assertEquals(validWorklog.withId("0"), service.list().get(0));
        }

        @Test
        public void readsClients() {
            assertEquals(validWorklog.withId("0"), service.read("0"));
        }

        @Test
        public void updatesClientById() {
            Worklog client = service.update("0", validWorklog.withDescription("updated"));
            assertEquals(validWorklog.withId("0").withDescription("updated"), client);
            assertEquals(client, service.read("0"));
        }

        @Test
        public void deletesClientById() {
            Worklog client = service.delete("0");
            assertEquals(validWorklog.withId("0"), client);
            assertEquals(0, service.list().size());
        }
    }
}
