package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockWorklogTypeRepo;
import com.sadesoft.portal.core.model.WorklogType;
import com.sadesoft.portal.core.sample.WorklogTypeSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorklogTypeServiceTest {
    private MockWorklogTypeRepo repo;
    private WorklogTypeService service;
    private WorklogType validWorklogType;

    @BeforeEach
    public void setup() {
        repo = new MockWorklogTypeRepo();
        service = new WorklogTypeService(repo);
        validWorklogType = WorklogTypeSamples.validWorklogType.copy();
    }

    @Nested
    public class GivenServiceWithOneClient {
        @BeforeEach
        public void setup() {
            repo.create(validWorklogType);
        }

        @Test
        public void listsClients() {
            assertEquals(1, service.list().size());
            assertEquals(validWorklogType.withId("0"), service.list().get(0));
        }

        @Test
        public void readsClients() {
            assertEquals(validWorklogType.withId("0"), service.read("0"));
        }

        @Test
        public void updatesClientById() {
            WorklogType worklogType = service.update("0", validWorklogType.withName("updated"));
            assertEquals(validWorklogType.withId("0").withName("updated"), worklogType);
            assertEquals(worklogType, service.read("0"));
        }

        @Test
        public void deletesClientById() {
            WorklogType worklogType = service.delete("0");
            assertEquals(validWorklogType.withId("0"), worklogType);
            assertEquals(0, service.list().size());
        }
    }
}
