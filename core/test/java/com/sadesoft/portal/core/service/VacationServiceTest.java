package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.ClientError;
import com.sadesoft.portal.base.error.EmptyFieldError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.mock.MockVacationRepo;
import com.sadesoft.portal.core.model.Vacation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VacationServiceTest {
    private MockVacationRepo repo;
    private VacationService service;
    private Vacation validVacation;

    private void assertEmptyField(Vacation vacation, String field) {
        EmptyFieldError e = assertThrows(EmptyFieldError.class, () -> service.create(vacation));
        assertEquals("Field '" + field + "' should not be empty.", e.getMessage());
    }

    private void assertMissingField(Vacation vacation) {
        assertThrows(MissingFieldError.class, () -> service.create(vacation));
    }

    private void assertForbiddenField(Vacation vacation) {
        assertThrows(ClientError.class, () -> service.create(vacation));
    }

    @BeforeEach
    public void setup() {
        repo = new MockVacationRepo();
        service = new VacationService(repo);
        validVacation = new Vacation(
                null,
                "4",
                "6",
                LocalDateTime.of(2022,5,4,9,18),
                LocalDateTime.of(2022,5,4,9,19),
                5,
                "descriptionExample",
                null);
    }

    @Test
    public void createsVacations() {
        Vacation vacation = service.create(validVacation);
        assertEquals(validVacation.withId("0").withStatus("Waiting"), vacation);
        assertEquals(1, repo.list().size());
        assertEquals(vacation, repo.read("0"));
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingField(validVacation.withEmployeeId(null));
        assertMissingField(validVacation.withVacationCategoryId(null));
        assertMissingField(validVacation.withStartTime(null));
        assertMissingField(validVacation.withEndTime(null));
        assertMissingField(validVacation.withDayCount(null));
        assertMissingField(validVacation.withDescription(null));
    }

    @Test
    public void whenApprovalStatusIsFilled_throwsError() {
        assertForbiddenField(validVacation.withStatus("Approved"));
    }

    @Test
    public void whenDescriptionIsEmpty_throwsError() {
        assertEmptyField(validVacation.withDescription(""), "description");
    }

    @Nested
    public class GivenServiceWithOneVacation {
        @BeforeEach
        public void setup() {
            service.create(validVacation);
        }

        @Test
        public void listsVacations() {
            assertEquals(1, service.list().size());
            assertEquals(repo.read("0"), service.list().get(0));
            assertEquals(repo.read("0"), service.read("0"));
        }

        @Test
        public void listsByStatus() {
            assertEquals(1, service.listByStatus(null).size());
            assertEquals(1, service.listByStatus("Waiting").size());
            assertEquals(0, service.listByStatus("Approved").size());
        }

        @Test
        public void updatesVacationById() {
            Vacation vacation = repo.read("0");
            Vacation updatedVacation = service.update("0", validVacation.withDescription("updated"));
            assertEquals(vacation.withDescription("updated"), updatedVacation);
            assertEquals(updatedVacation, repo.read("0"));
        }

        @Test
        public void whenUpdatesVacation_makesStatusWaiting() {
            repo.update("0", repo.read("0").withStatus("Approved"));
            Vacation vacation = service.update("0", validVacation);
            assertEquals(validVacation.withId("0").withStatus("Waiting"), vacation);
        }

        @Test
        public void deletesVacationById() {
            Vacation firstVacation = repo.read("0");
            Vacation deletedVacation = service.delete("0");
            assertEquals(firstVacation, deletedVacation);
            assertEquals(0, service.list().size());
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError(){
            assertThrows(MissingFieldError.class, () -> service.update("0", validVacation.withEmployeeId(null)));
            assertThrows(MissingFieldError.class, () -> service.update("0", validVacation.withStartTime(null)));
        }
    }
}
