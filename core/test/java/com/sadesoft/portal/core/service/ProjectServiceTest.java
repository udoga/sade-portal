package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.query.ProjectQuery;
import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.mock.MockClientRepo;
import com.sadesoft.portal.core.mock.MockProjectRepo;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void createsProject() {
        Project project = service.create(validProject);
        assertEquals(validProject.withId("0"), project);
        assertEquals(1, projectRepo.list().size());
        assertEquals(project, projectRepo.read("0"));
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingFieldOnCreate(validProject.withClientId(null));
        assertMissingFieldOnCreate(validProject.withName(null));
        assertMissingFieldOnCreate(validProject.withDescription(null));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() {
        assertInvalidInputOnCreate(validProject.withClientId("A"));
        assertInvalidInputOnCreate(validProject.withClientId(""));
        assertInvalidInputOnCreate(validProject.withName("1"));
        assertInvalidInputOnCreate(validProject.withName(""));
        assertInvalidInputOnCreate(validProject.withDescription("1"));
        assertInvalidInputOnCreate(validProject.withDescription(""));
        assertDoesNotThrow(() -> service.create(validProject.withClientId("11")));
        assertDoesNotThrow(() -> service.create(validProject.withName("A B")));
        assertDoesNotThrow(() -> service.create(validProject.withDescription("A B")));
    }

    private void assertMissingFieldOnCreate(Project project) {
        assertThrows(MissingFieldError.class, () -> service.create(project));
    }

    private void assertInvalidInputOnCreate(Project project) {
        assertThrows(InvalidInputError.class, () -> service.create(project));
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

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError() {
            assertMissingFieldOnUpdate(validProject.withClientId(null));
            assertMissingFieldOnUpdate(validProject.withName(null));
            assertMissingFieldOnUpdate(validProject.withDescription(null));
        }

        @Test
        public void whenHasInvalidInputOnUpdate_throwsError() {
            assertInvalidInputOnUpdate(validProject.withClientId("A"));
            assertInvalidInputOnUpdate(validProject.withClientId(""));
            assertInvalidInputOnUpdate(validProject.withName("1"));
            assertInvalidInputOnUpdate(validProject.withName(""));
            assertInvalidInputOnUpdate(validProject.withDescription("1"));
            assertInvalidInputOnUpdate(validProject.withDescription(""));
            assertDoesNotThrow(() -> service.update("0", validProject.withClientId("11")));
            assertDoesNotThrow(() -> service.update("0", validProject.withName("A B")));
            assertDoesNotThrow(() -> service.update("0", validProject.withDescription("A B")));
        }

        private void assertMissingFieldOnUpdate(Project project) {
            assertThrows(MissingFieldError.class, () -> service.update("0", project));
        }

        private void assertInvalidInputOnUpdate(Project project) {
            assertThrows(InvalidInputError.class, () -> service.update("0", project));
        }
    }
}
