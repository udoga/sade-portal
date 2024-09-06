package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.query.ProjectQuery;
import com.sadesoft.portal.core.mock.MockClientRepo;
import com.sadesoft.portal.core.mock.MockProjectRepo;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProjectServiceTest {
    private MockClientRepo clientRepo;
    private MockProjectRepo projectRepo;
    private ProjectService service;
    private Project validProject;

    @BeforeEach
    public void setup() {
        clientRepo = new MockClientRepo(null);
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
            List<Project> projects = service.listBy(new ProjectQuery());
            assertEquals(1, projects.size());
            assertEquals(validProject.withId("0"), projects.get(0));
        }

        @Test
        public void listsProjectOptions() {
            assertEquals(1, service.listOptions().size());
            assertNull(service.listOptions().get(0).clientId);
        }

        @Test
        public void listsProjectsByClientId() {
            clientRepo.create(new Client(null, "Sample Client", null));
            assertEquals(1, service.listBy(new ProjectQuery().withClientId("0")).size());
            assertEquals(0, service.listBy(new ProjectQuery().withClientId("1")).size());
        }

        @Test
        public void listsDetailedProjects() {
            clientRepo.create(new Client(null, "Sample Client", null));
            assertEquals(1, service.listDetailedBy(new ProjectQuery()).size());
            assertEquals(projectRepo.listDetailedBy(new ProjectQuery()), service.listDetailedBy(new ProjectQuery()));
        }

        @Test
        public void listsDetailedProjectsByClientId() {
            clientRepo.create(new Client(null, "Sample Client", null));
            ProjectQuery query = new ProjectQuery().withClientId("0");
            assertEquals(1, service.listDetailedBy(query).size());
            assertEquals(clientRepo.read("0"), service.listDetailedBy(query).get(0).client);
            assertEquals(0, service.listDetailedBy(query.withClientId("1")).size());
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
            assertEquals(0, projectRepo.list().size());
        }
    }
}
