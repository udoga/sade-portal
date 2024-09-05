package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.query.EngagementQuery;
import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertMissingFieldOnCreate(validEngagement.withProjectId(null));
        assertMissingFieldOnCreate(validEngagement.withEmployeeId(null));
        assertMissingFieldOnCreate(validEngagement.withPositionId(null));
        assertMissingFieldOnCreate(validEngagement.withStartDate(null));
        assertMissingFieldOnCreate(validEngagement.withEndDate(null));
        assertMissingFieldOnCreate(validEngagement.withDescription(null));
        assertMissingFieldOnCreate(validEngagement.withStatus(null));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() {
        assertInvalidInputOnCreate(validEngagement.withProjectId("A"));
        assertInvalidInputOnCreate(validEngagement.withProjectId(""));
        assertInvalidInputOnCreate(validEngagement.withEmployeeId("A"));
        assertInvalidInputOnCreate(validEngagement.withEmployeeId(""));
        assertInvalidInputOnCreate(validEngagement.withPositionId("A"));
        assertInvalidInputOnCreate(validEngagement.withPositionId(""));
        assertInvalidInputOnCreate(validEngagement.withDescription("1"));
        assertInvalidInputOnCreate(validEngagement.withDescription(""));
        assertInvalidInputOnCreate(validEngagement.withStatus("1"));
        assertInvalidInputOnCreate(validEngagement.withStatus(""));
        assertInvalidInputOnCreate(validEngagement.withStatus("C"));
        assertDoesNotThrow(() -> service.create(validEngagement.withProjectId("11")));
        assertDoesNotThrow(() -> service.create(validEngagement.withEmployeeId("11")));
        assertDoesNotThrow(() -> service.create(validEngagement.withPositionId("11")));
        assertDoesNotThrow(() -> service.create(validEngagement.withDescription("A B")));
    }

    private void assertMissingFieldOnCreate(Engagement engagement) {
        assertThrows(MissingFieldError.class, () -> service.create(engagement));
    }

    private void assertInvalidInputOnCreate(Engagement engagement) {
        assertThrows(InvalidInputError.class, () -> service.create(engagement));
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
            assertMissingFieldOnUpdate(validEngagement.withProjectId(null));
            assertMissingFieldOnUpdate(validEngagement.withEmployeeId(null));
            assertMissingFieldOnUpdate(validEngagement.withPositionId(null));
            assertMissingFieldOnUpdate(validEngagement.withStartDate(null));
            assertMissingFieldOnUpdate(validEngagement.withEndDate(null));
            assertMissingFieldOnUpdate(validEngagement.withDescription(null));
            assertMissingFieldOnUpdate(validEngagement.withStatus(null));
        }

        @Test
        public void whenHasInvalidInputOnUpdate_throwsError() {
            assertInvalidInputOnUpdate(validEngagement.withProjectId("A"));
            assertInvalidInputOnUpdate(validEngagement.withProjectId(""));
            assertInvalidInputOnUpdate(validEngagement.withEmployeeId("A"));
            assertInvalidInputOnUpdate(validEngagement.withEmployeeId(""));
            assertInvalidInputOnUpdate(validEngagement.withPositionId("A"));
            assertInvalidInputOnUpdate(validEngagement.withPositionId(""));
            assertInvalidInputOnUpdate(validEngagement.withDescription("1"));
            assertInvalidInputOnUpdate(validEngagement.withDescription(""));
            assertInvalidInputOnUpdate(validEngagement.withStatus("1"));
            assertInvalidInputOnUpdate(validEngagement.withStatus(""));
            assertInvalidInputOnUpdate(validEngagement.withStatus("C"));
            assertDoesNotThrow(() -> service.update("0", validEngagement.withProjectId("11")));
            assertDoesNotThrow(() -> service.update("0", validEngagement.withEmployeeId("11")));
            assertDoesNotThrow(() -> service.update("0", validEngagement.withPositionId("11")));
            assertDoesNotThrow(() -> service.update("0", validEngagement.withDescription("A B")));
        }

        private void assertMissingFieldOnUpdate(Engagement engagement) {
            assertThrows(MissingFieldError.class, () -> service.update("0", engagement));
        }

        private void assertInvalidInputOnUpdate(Engagement engagement) {
            assertThrows(InvalidInputError.class, () -> service.update("0", engagement));
        }
    }
}
