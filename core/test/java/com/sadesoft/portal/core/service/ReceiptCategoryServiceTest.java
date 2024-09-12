package com.sadesoft.portal.core.service;

import static com.sadesoft.portal.base.util.TestUtil.*;
import com.sadesoft.portal.core.mock.MockReceiptCategoryRepo;
import com.sadesoft.portal.core.model.ReceiptCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

public class ReceiptCategoryServiceTest {
    private MockReceiptCategoryRepo receiptCategoryRepo;
    private ReceiptCategoryService service;
    private ReceiptCategory validReceiptCategory;

    @BeforeEach
    public void setup() {
        receiptCategoryRepo = new MockReceiptCategoryRepo();
        service = new ReceiptCategoryService(receiptCategoryRepo);
        validReceiptCategory = new ReceiptCategory(null, "Example ReceiptCategory");
    }

    @Test
    public void createsReceiptCategory() {
        ReceiptCategory receiptCategory = service.create(validReceiptCategory);
        assertEquals(1, receiptCategoryRepo.list().size());
        assertEquals(receiptCategory, receiptCategoryRepo.read("0"));
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingField(() -> service.create(validReceiptCategory.withName(null)));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() throws Exception {
        assertSpacedField("name");

    }

    private void assertSpacedField(String fieldName) throws Exception {
        assertInvalidInput(() -> service.create(withField(validReceiptCategory, fieldName, "1")));
        assertInvalidInput(() -> service.create(withField(validReceiptCategory, fieldName, "")));
        assertInvalidInput(() -> service.create(withField(validReceiptCategory, fieldName, " ")));
        assertInvalidInput(() -> service.create(withField(validReceiptCategory, fieldName, "a ")));
        assertDoesNotThrow(() -> service.create(withField(validReceiptCategory, fieldName, "A B")));
    }

    private ReceiptCategory withField(ReceiptCategory receiptCategory, String fieldName, String value) throws Exception {
        Field field = receiptCategory.getClass().getDeclaredField(fieldName);
        field.set(receiptCategory, value);
        return receiptCategory;
    }

    @Test
    public void readsReceiptCategory() {
        service.create(validReceiptCategory);
        assertEquals(validReceiptCategory.withId("0"), service.read("0"));
    }

    @Test
    public void listsReceiptCategories() {
        service.create(validReceiptCategory);
        assertEquals(receiptCategoryRepo.list(), service.list());
    }

    @Test
    public void listsReceiptCategoryOptions() {
        service.create(validReceiptCategory);
        assertEquals(receiptCategoryRepo.listOptions(), service.listOptions());
    }

    @Test
    public void deletesReceiptCategoryById() {
        service.create(validReceiptCategory);
        ReceiptCategory receiptCategory = service.delete("0");
        assertEquals(validReceiptCategory.withId("0"), receiptCategory);
        assertEquals(0, service.list().size());
    }
}
