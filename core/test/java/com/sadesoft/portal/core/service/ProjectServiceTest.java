package com.sadesoft.portal.core.service;

import static com.sadesoft.portal.base.util.TestUtil.*;
import com.sadesoft.portal.core.query.ProjectQuery;
import com.sadesoft.portal.core.mock.MockClientRepo;
import com.sadesoft.portal.core.mock.MockProjectRepo;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

    @Test
    public void createsProject() {
        Project project = service.create(validProject);
        assertEquals(validProject.withId("0"), project);
        assertEquals(1, projectRepo.list().size());
        assertEquals(project, projectRepo.read("0"));
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingField(() -> service.create(validProject.withClientId(null)));
        assertMissingField(() -> service.create(validProject.withName(null)));
        assertMissingField(() -> service.create(validProject.withDescription(null)));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() throws Exception {
        assertIdField("clientId");
        assertSpacedField("name");
    }

    private void assertIdField(String fieldName) throws Exception {
        assertInvalidInput(() -> service.create(withField(validProject, fieldName, "A")));
        assertInvalidInput(() -> service.create(withField(validProject, fieldName, "")));
        assertDoesNotThrow(() -> service.create(withField(validProject, fieldName, "11")));
    }

    private void assertSpacedField(String fieldName) throws Exception {
        assertInvalidInput(() -> service.create(withField(validProject, fieldName, "1")));
        assertInvalidInput(() -> service.create(withField(validProject, fieldName, "")));
        assertInvalidInput(() -> service.create(withField(validProject, fieldName, " ")));
        assertInvalidInput(() -> service.create(withField(validProject, fieldName, "a ")));
        assertDoesNotThrow(() -> service.create(withField(validProject, fieldName, "A B")));
        assertDoesNotThrow(() -> service.create(withField(validProject, fieldName, "ğ")));
    }

    private Project withField(Project project, String fieldName, String value) throws Exception {
        Field field = project.getClass().getDeclaredField(fieldName);
        field.set(project, value);
        return project;
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
            assertMissingField(() -> service.update("0", validProject.withClientId(null)));
            assertMissingField(() -> service.update("0", validProject.withName(null)));
            assertMissingField(() -> service.update("0", validProject.withDescription(null)));
        }
    }
}
