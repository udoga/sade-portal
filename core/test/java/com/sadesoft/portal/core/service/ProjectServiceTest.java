package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockClientRepo;
import com.sadesoft.portal.core.mock.MockProjectRepo;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectServiceTest {
    private MockClientRepo clientRepo;
    private MockProjectRepo projectRepo;
    private ProjectService service;
    private Project validProject;

    @BeforeEach
    public void setup() {
        clientRepo = new MockClientRepo();
        projectRepo = new MockProjectRepo(clientRepo);
        service = new ProjectService(projectRepo);
        validProject = new Project(
                null,
                "0",
                "Sample Project",
                "Sample Description");
    }

    @Nested
    public class GivenServiceWithOneProject {
        @BeforeEach
        public void setup() {
            projectRepo.create(validProject);
        }

        @Test
        public void listsProjects() {
            assertEquals(1, service.list().size());
            assertEquals(validProject.withId("0"), service.list().get(0));
        }

        @Test
        public void listsProjectsByClientId() {
            clientRepo.create(new Client(null, "Sample Client", null));
            assertEquals(1, service.listBy("0").size());
            assertEquals(0, service.listBy("1").size());
        }

        @Test
        public void listsDetailedProjects() {
            clientRepo.create(new Client(null, "Sample Client", null));
            assertEquals(1, service.listDetailedBy(null).size());
            assertEquals(projectRepo.listDetailedBy(null), service.listDetailedBy(null));
        }

        @Test
        public void listsDetailedProjectsByClientId() {
            clientRepo.create(new Client(null, "Sample Client", null));
            assertEquals(1, service.listDetailedBy("0").size());
            assertEquals(clientRepo.read("0"), service.listDetailedBy("0").get(0).client);
            assertEquals(0, service.listDetailedBy("1").size());
        }

        @Test
        public void readsProject() {
            assertEquals(validProject.withId("0"), service.read("0"));
        }

        @Test
        public void readsProjectWithClient() {
            clientRepo.create(new Client(null, "Sample Client", null));
            assertEquals(projectRepo.readDetailed("0"), service.readDetailed("0"));
        }

        @Test
        public void updatesProjectById() {
            Project client = service.update("0", validProject.withName("updated"));
            assertEquals(validProject.withId("0").withName("updated"), client);
            assertEquals(client, service.read("0"));
        }

        @Test
        public void deletesProjectById() {
            Project client = service.delete("0");
            assertEquals(validProject.withId("0"), client);
            assertEquals(0, service.list().size());
        }
    }
}
