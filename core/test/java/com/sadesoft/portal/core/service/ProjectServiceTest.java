package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockProjectRepo;
import com.sadesoft.portal.core.model.Project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectServiceTest {
    private MockProjectRepo repo;
    private ProjectService service;
    private Project validProject;

    @BeforeEach
    public void setup() {
        repo = new MockProjectRepo();
        service = new ProjectService(repo);
        validProject = new Project(
                null,
                "1",
                "Sample Project",
                "Sample Description");
    }

    @Nested
    public class GivenServiceWithOneClient {
        @BeforeEach
        public void setup() {
            repo.create(validProject);
        }

        @Test
        public void listsClients() {
            assertEquals(1, service.list().size());
            assertEquals(validProject.withId("0"), service.list().get(0));
        }

        @Test
        public void readsClients() {
            assertEquals(validProject.withId("0"), service.read("0"));
        }

        @Test
        public void updatesClientById() {
            Project client = service.update("0", validProject.withName("updated"));
            assertEquals(validProject.withId("0").withName("updated"), client);
            assertEquals(client, service.read("0"));
        }

        @Test
        public void deletesClientById() {
            Project client = service.delete("0");
            assertEquals(validProject.withId("0"), client);
            assertEquals(0, service.list().size());
        }
    }
}
