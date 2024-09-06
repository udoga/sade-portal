package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidInputError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.mock.MockReceiptCategoryRepo;
import com.sadesoft.portal.core.model.ReceiptCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertMissingFieldOnCreate(validReceiptCategory.withName(null));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() {
        assertInvalidInputOnCreate(validReceiptCategory.withName("1"));
        assertInvalidInputOnCreate(validReceiptCategory.withName(""));
        assertDoesNotThrow(() -> service.create(validReceiptCategory.withName("A B")));

    }

    private void assertMissingFieldOnCreate(ReceiptCategory receiptCategory) {
        assertThrows(MissingFieldError.class, () -> service.create(receiptCategory));
    }

    private void assertInvalidInputOnCreate(ReceiptCategory receiptCategory) {
        assertThrows(InvalidInputError.class, () -> service.create(receiptCategory));
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
