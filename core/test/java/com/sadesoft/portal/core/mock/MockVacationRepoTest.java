package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.core.model.ApprovalStatus;
import com.sadesoft.portal.core.model.Vacation;
import com.sadesoft.portal.core.model.VacationType;
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

    @BeforeEach
    public void setup() {
        vacationRepo = new MockVacationRepo();
        validVacation = new Vacation(
                null,
                "4",
                VacationType.AnnualVacation,
                "6",
                LocalDateTime.of(2022,5,4,9,18),
                LocalDateTime.of(2022,5,4,9,19),
                5,
                "descriptionExample",
                null);
    }

    @Test
    public void whenVacationStatusIsNull_doesNotFilter() {
        vacationRepo.create(validVacation.withStatus(ApprovalStatus.Waiting));
        assertEquals(vacationRepo.list(), vacationRepo.paginateByStatus(1, 5, null));
    }

    @Test
    public void whenThereIsOneVacation_filtersByStatus() {
        vacationRepo.create(validVacation.withStatus(ApprovalStatus.Approved));
        assertEquals(vacationRepo.list(), vacationRepo.paginateByStatus(1, 5, ApprovalStatus.Approved));
        assertEquals(new ArrayList<Vacation>(), vacationRepo.paginateByStatus(1, 5, ApprovalStatus.Rejected));
    }

    @Test
    public void whenTwoVacationsHaveDifferentStatus_filtersByStatus() {
        vacationRepo.create(validVacation.withStatus(ApprovalStatus.Rejected));
        vacationRepo.create(validVacation.withStatus(ApprovalStatus.Approved));
        List<Vacation> filteredVacations = vacationRepo.paginateByStatus(1, 5, ApprovalStatus.Rejected);
        assertEquals(1, filteredVacations.size());
        assertEquals(ApprovalStatus.Rejected, filteredVacations.get(0).status);
    }
}


