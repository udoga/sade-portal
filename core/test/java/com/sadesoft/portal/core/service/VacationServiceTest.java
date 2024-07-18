package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.ClientError;
import com.sadesoft.portal.base.error.EmptyFieldError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.mock.MockEmployeeRepo;
import com.sadesoft.portal.core.mock.MockVacationRepo;
import com.sadesoft.portal.core.mock.MockVacationCategoryRepo;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Vacation;
import com.sadesoft.portal.core.model.VacationCategory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VacationServiceTest {
    private MockEmployeeRepo employeeRepo;
    private MockVacationCategoryRepo categoryRepo;
    private MockVacationRepo vacationRepo;
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
        employeeRepo = new MockEmployeeRepo(null);
        categoryRepo = new MockVacationCategoryRepo();
        vacationRepo = new MockVacationRepo(employeeRepo, categoryRepo);
        service = new VacationService(vacationRepo);
        validVacation = new Vacation(
                null,
                "0",
                "0",
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
        assertEquals(1, vacationRepo.list().size());
        assertEquals(vacation, vacationRepo.read("0"));
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
            assertEquals(vacationRepo.read("0"), service.list().get(0));
            assertEquals(vacationRepo.read("0"), service.read("0"));
        }

        @Test
        public void listsByStatus() {
            assertEquals(1, service.listByStatus(null).size());
            assertEquals(1, service.listByStatus("Waiting").size());
            assertEquals(0, service.listByStatus("Approved").size());
        }

        @Test
        public void listsDetailedVacations() {
            employeeRepo.create(new Employee());
            categoryRepo.create(new VacationCategory());
            assertEquals(vacationRepo.listDetailed(), service.listDetailed());
        }

        @Test
        public void readsDetailedVacation() {
            employeeRepo.create(new Employee(null, null, "first", "last"));
            categoryRepo.create(new VacationCategory(null, "category"));
            assertEquals(vacationRepo.readDetailed("0"), service.readDetailed("0"));
        }

        @Test
        public void updatesVacationById() {
            Vacation vacation = vacationRepo.read("0");
            Vacation updatedVacation = service.update("0", validVacation.withDescription("updated"));
            assertEquals(vacation.withDescription("updated"), updatedVacation);
            assertEquals(updatedVacation, vacationRepo.read("0"));
        }

        @Test
        public void whenUpdatesVacation_makesStatusWaiting() {
            vacationRepo.update("0", vacationRepo.read("0").withStatus("Approved"));
            Vacation vacation = service.update("0", validVacation);
            assertEquals(validVacation.withId("0").withStatus("Waiting"), vacation);
        }

        @Test
        public void deletesVacationById() {
            Vacation firstVacation = vacationRepo.read("0");
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
