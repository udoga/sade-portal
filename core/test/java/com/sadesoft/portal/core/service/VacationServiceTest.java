package com.sadesoft.portal.core.service;

import static com.sadesoft.portal.base.util.TestUtil.*;
import com.sadesoft.portal.base.error.ClientError;
import com.sadesoft.portal.core.query.VacationQuery;
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
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VacationServiceTest {
    private MockEmployeeRepo employeeRepo;
    private MockVacationCategoryRepo categoryRepo;
    private MockVacationRepo vacationRepo;
    private VacationService service;
    private Vacation validVacation;

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
        assertEquals(validVacation.withId("0").withApprovalStatus("Waiting"), vacation);
        assertEquals(1, vacationRepo.list().size());
        assertEquals(vacation, vacationRepo.read("0"));
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingField(() -> service.create(validVacation.withEmployeeId(null)));
        assertMissingField(() -> service.create(validVacation.withVacationCategoryId(null)));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() throws Exception{
        assertIdField("employeeId");
        assertIdField("categoryId");
    }

    @Test
    public void whenApprovalStatusIsFilled_throwsError() {
        assertForbiddenField(validVacation.withApprovalStatus("Approved"));
    }

    private void assertIdField(String fieldName) throws Exception {
        assertInvalidInput(() -> service.create(withField(validVacation, fieldName, "A")));
        assertInvalidInput(() -> service.create(withField(validVacation, fieldName, "")));
        assertDoesNotThrow(() -> service.create(withField(validVacation, fieldName, "11")));
    }

    private Vacation withField(Vacation vacation, String fieldName, String value) throws Exception {
        Field field = vacation.getClass().getDeclaredField(fieldName);
        field.set(vacation, value);
        return vacation;
    }

    @Nested
    public class GivenServiceWithOneVacation {
        @BeforeEach
        public void setup() {
            service.create(validVacation);
        }

        @Test
        public void listsVacations() {
            assertEquals(1, service.listBy(new VacationQuery()).size());
            assertEquals(vacationRepo.read("0"), service.listBy(new VacationQuery()).get(0));
            assertEquals(vacationRepo.read("0"), service.read("0"));
        }

        @Test
        public void listsByApprovalStatus() {
            assertEquals(1, service.listBy(new VacationQuery()).size());
            assertEquals(1, service.listBy(new VacationQuery().withApprovalStatus("Waiting")).size());
            assertEquals(0, service.listBy(new VacationQuery().withApprovalStatus("Approved")).size());
        }

        @Test
        public void listsDetailedVacations() {
            employeeRepo.create(new Employee());
            categoryRepo.create(new VacationCategory());
            assertEquals(vacationRepo.listDetailedBy(new VacationQuery()), service.listDetailedBy(new VacationQuery()));
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
            vacationRepo.update("0", vacationRepo.read("0").withApprovalStatus("Approved"));
            Vacation vacation = service.update("0", validVacation);
            assertEquals(validVacation.withId("0").withApprovalStatus("Waiting"), vacation);
        }

        @Test
        public void deletesVacationById() {
            Vacation firstVacation = vacationRepo.read("0");
            Vacation deletedVacation = service.delete("0");
            assertEquals(firstVacation, deletedVacation);
            assertEquals(0, vacationRepo.list().size());
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError(){
            assertMissingField(() -> service.update("0", validVacation.withEmployeeId(null)));
            assertMissingField(() -> service.update("0", validVacation.withVacationCategoryId(null)));
        }
    }
}
