package com.sadesoft.portal.core.service;

import static com.sadesoft.portal.base.util.TestUtil.*;
import com.sadesoft.portal.core.query.EngagementQuery;
import com.sadesoft.portal.core.mock.MockEmployeeRepo;
import com.sadesoft.portal.core.mock.MockEngagementRepo;
import com.sadesoft.portal.core.mock.MockPositionRepo;
import com.sadesoft.portal.core.mock.MockProjectRepo;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Engagement;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class EngagementServiceTest {
    private MockProjectRepo projectRepo;
    private MockEmployeeRepo employeeRepo;
    private MockPositionRepo positionRepo;
    private MockEngagementRepo engagementRepo;
    private EngagementService service;
    private Engagement validEngagement;

    @BeforeEach
    public void setup() {
        projectRepo = new MockProjectRepo(null);
        employeeRepo = new MockEmployeeRepo(null);
        positionRepo = new MockPositionRepo();
        engagementRepo = new MockEngagementRepo(projectRepo, employeeRepo, positionRepo);
        service = new EngagementService(engagementRepo);
        validEngagement = new Engagement(null, "0", "0", "0", LocalDate.of(2010, 1, 10), LocalDate.of(2010, 1, 11),
        "description", "A");
    }

    @Test
    public void createsEngagement() {
        Engagement engagement = service.create(validEngagement);
        assertEquals(validEngagement.withId("0"), engagement);
        assertEquals(1, engagementRepo.list().size());
        assertEquals(engagement, engagementRepo.read("0"));
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingField(() -> service.create(validEngagement.withProjectId(null)));
        assertMissingField(() -> service.create(validEngagement.withEmployeeId(null)));
        assertMissingField(() -> service.create(validEngagement.withPositionId(null)));
        assertMissingField(() -> service.create(validEngagement.withStartDate(null)));
        assertMissingField(() -> service.create(validEngagement.withEndDate(null)));
        assertMissingField(() -> service.create(validEngagement.withDescription(null)));
        assertMissingField(() -> service.create(validEngagement.withStatus(null)));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() throws Exception {
        assertIdField("projectId");
        assertIdField("employeeId");
        assertIdField("positionId");
        assertInvalidInput(() -> service.create(withField(validEngagement, "status", "1")));
        assertInvalidInput(() -> service.create(withField(validEngagement, "status", "")));
        assertInvalidInput(() -> service.create(withField(validEngagement, "status", "C")));
    }

    private void assertIdField(String fieldName) throws Exception {
        assertInvalidInput(() -> service.create(withField(validEngagement, fieldName, "A")));
        assertInvalidInput(() -> service.create(withField(validEngagement, fieldName, "")));
        assertDoesNotThrow(() -> service.create(withField(validEngagement, fieldName, "11")));
    }

    private Engagement withField(Engagement engagement, String fieldName, String value) throws Exception {
        Field field = engagement.getClass().getDeclaredField(fieldName);
        field.set(engagement, value);
        return engagement;
    }

    @Nested
    public class GivenServiceWithOneEngagement {
        @BeforeEach
        public void setup() {
            engagementRepo.create(validEngagement);
        }

        @Test
        public void listsEngagements() {
            List<Engagement> engagements = service.listBy(new EngagementQuery());
            assertEquals(1, engagements.size());
            assertEquals(validEngagement.withId("0"), engagements.get(0));
        }

        @Test
        public void listsByProjectId() {
            assertEquals(1, service.listBy(new EngagementQuery()).size());
            assertEquals(1, service.listBy(new EngagementQuery().withProjectId("0")).size());
            assertEquals(0, service.listBy(new EngagementQuery().withProjectId("1")).size());
        }

        @Test
        public void listsDetailedEngagements() {
            projectRepo.create(new Project());
            employeeRepo.create(new Employee());
            positionRepo.create(new Position());
            assertEquals(engagementRepo.listDetailedBy(new EngagementQuery()), service.listDetailedBy(new EngagementQuery()));
        }

        @Test
        public void readsEngagement() {
            assertEquals(validEngagement.withId("0"), service.read("0"));
        }

        @Test
        public void updatesEngagementById() {
            Engagement engagement = service.update("0", validEngagement.withDescription("updated"));
            assertEquals(validEngagement.withId("0").withDescription("updated"), engagement);
            assertEquals(engagement, service.read("0"));
        }

        @Test
        public void deletesEngagementById() {
            Engagement engagement = service.delete("0");
            assertEquals(validEngagement.withId("0"), engagement);
            assertEquals(0, engagementRepo.list().size());
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError() {
            assertMissingField(() -> service.update("0", validEngagement.withProjectId(null)));
            assertMissingField(() -> service.update("0", validEngagement.withEmployeeId(null)));
            assertMissingField(() -> service.update("0", validEngagement.withPositionId(null)));
            assertMissingField(() -> service.update("0", validEngagement.withStartDate(null)));
            assertMissingField(() -> service.update("0", validEngagement.withEndDate(null)));
            assertMissingField(() -> service.update("0", validEngagement.withDescription(null)));
            assertMissingField(() -> service.update("0", validEngagement.withStatus(null)));
        }
    }
}
