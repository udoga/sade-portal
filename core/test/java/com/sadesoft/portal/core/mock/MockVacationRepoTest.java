package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.core.model.Vacation;
import com.sadesoft.portal.core.repo.EmployeeRepo;
import com.sadesoft.portal.core.repo.VacationCategoryRepo;
import com.sadesoft.portal.core.repo.VacationRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockVacationRepoTest {
    VacationRepo vacationRepo;
    Vacation validVacation;
    EmployeeRepo employeeRepo;
    VacationCategoryRepo categoryRepo;

    @BeforeEach
    public void setup() {
        employeeRepo = null;
        categoryRepo = null;
        vacationRepo = new MockVacationRepo(employeeRepo, categoryRepo);
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
    public void whenThereIsOneVacation_filtersByStatus() {
        vacationRepo.create(validVacation.withStatus("Approved"));
        assertEquals(vacationRepo.list(), vacationRepo.listByStatus("Approved"));
        assertEquals(new ArrayList<Vacation>(), vacationRepo.listByStatus("Rejected"));
    }

    @Test
    public void whenTwoVacationsHaveDifferentStatus_filtersByStatus() {
        vacationRepo.create(validVacation.withStatus("Rejected"));
        vacationRepo.create(validVacation.withStatus("Approved"));
        List<Vacation> filteredVacations = vacationRepo.listByStatus("Rejected");
        assertEquals(1, filteredVacations.size());
        assertEquals("Rejected", filteredVacations.get(0).status);
    }
}
