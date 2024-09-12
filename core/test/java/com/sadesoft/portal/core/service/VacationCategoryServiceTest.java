package com.sadesoft.portal.core.service;

import static com.sadesoft.portal.base.util.TestUtil.*;
import com.sadesoft.portal.core.mock.MockVacationCategoryRepo;
import com.sadesoft.portal.core.model.VacationCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

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
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingField(() -> service.create(validVacationCategory.withName(null)));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() throws Exception{
        assertSpacedField("name");
    }

    private void assertSpacedField(String fieldName) throws Exception {
        assertInvalidInput(() -> service.create(withField(validVacationCategory, fieldName, "1")));
        assertInvalidInput(() -> service.create(withField(validVacationCategory, fieldName, "")));
        assertInvalidInput(() -> service.create(withField(validVacationCategory, fieldName, " ")));
        assertInvalidInput(() -> service.create(withField(validVacationCategory, fieldName, "a ")));
        assertDoesNotThrow(() -> service.create(withField(validVacationCategory, fieldName, "A B")));
    }

    private VacationCategory withField(VacationCategory vacationCategory, String fieldName, String value) throws Exception {
        Field field = vacationCategory.getClass().getDeclaredField(fieldName);
        field.set(vacationCategory, value);
        return vacationCategory;
    }

    @Test
    public void readVacationCategory() {
        service.create(validVacationCategory);
        assertEquals(validVacationCategory.withId("0"), service.read("0"));
    }

    @Test
    public void listsVacationCategories() {
        service.create(validVacationCategory);
        assertEquals(vacationCategoryRepo.list(), service.list());
    }

    @Test
    public void listsVacationCategoryOptions() {
        service.create(validVacationCategory);
        assertEquals(vacationCategoryRepo.listOptions(), service.listOptions());
    }

    @Test
    public void deletesVacationCategoryById() {
        service.create(validVacationCategory);
        VacationCategory vacationCategory = service.delete("0");
        assertEquals(validVacationCategory.withId("0"), vacationCategory);
        assertEquals(0, service.list().size());
    }
}
