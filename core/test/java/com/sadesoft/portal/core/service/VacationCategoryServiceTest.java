package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockVacationCategoryRepo;
import com.sadesoft.portal.core.model.VacationCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VacationCategoryServiceTest {
    private MockVacationCategoryRepo vacationCategoryRepo;
    private VacationCategoryService service;
    private VacationCategory validVacationCategory;

    @BeforeEach
    public void setup() {
        vacationCategoryRepo = new MockVacationCategoryRepo();
        service = new VacationCategoryService(vacationCategoryRepo);
        validVacationCategory = new VacationCategory(null, "Example VacationCategory");
    }

    @Test
    public void createsVacationCategory() {
        VacationCategory vacationCategory = service.create(validVacationCategory);
        assertEquals(1, vacationCategoryRepo.list().size());
        assertEquals(vacationCategory, vacationCategoryRepo.read("0"));
    }

    @Test
    public void readVacationCategory() {
        service.create(validVacationCategory);
        assertEquals(validVacationCategory.withId("0"), service.read("0"));
    }

    @Test
    public void listsVacationCategorys() {
        service.create(validVacationCategory);
        assertEquals(vacationCategoryRepo.list(), service.list());
    }

    @Test
    public void deletesVacationCategoryById() {
        service.create(validVacationCategory);
        VacationCategory vacationCategory = service.delete("0");
        assertEquals(validVacationCategory.withId("0"), vacationCategory);
        assertEquals(0, service.list().size());
    }
}
