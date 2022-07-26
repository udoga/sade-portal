package com.sadesoft.portal.core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.sadesoft.portal.base.repo.ListRepo;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.sample.ReceiptSamples;

public class ReceiptServiceTest {
    private ListRepo<Receipt> repo;
    private ReceiptService service;
    private Receipt validReceipt;

    @BeforeEach
    public void setup() {
        repo = new ListRepo<>("Receipt");
        service = new ReceiptService(repo);
        validReceipt = ReceiptSamples.validReceipt;
    }

    @Test
    public void createsReceipt() {
        Receipt receipt = service.create(validReceipt);
        assertEquals(validReceipt.withId(0), receipt);
        assertEquals(1, repo.list().size());
        assertEquals(receipt, repo.read(0));
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingFieldOnCreate(validReceipt.withReceiptDate(null));
        assertMissingFieldOnCreate(validReceipt.withReceiptCategory(null));
        assertMissingFieldOnCreate(validReceipt.withAmount(null));
        assertMissingFieldOnCreate(validReceipt.withVatRate(null));
        assertMissingFieldOnCreate(validReceipt.withDescription(null));
    }

    private void assertMissingFieldOnCreate(Receipt receipt) {
        assertThrows(MissingFieldError.class, () -> service.create(receipt));
    }

    @Nested
    public class GivenServiceWithOneReceipt {
        @BeforeEach
        public void setup() {
            service.create(validReceipt);
        }

        @Test
        public void listsReceipts() {
            assertEquals(1, service.list().size());
            assertEquals(validReceipt.withId(0), service.list().get(0));
            assertEquals(validReceipt.withId(0), service.read(0));
        }

        @Test
        public void updatesReceiptById() {
            Receipt receipt = service.update(0, validReceipt.withDescription("updated"));
            assertEquals(validReceipt.withId(0).withDescription("updated"), receipt);
            assertEquals(receipt, repo.read(0));
        }

        @Test
        public void deletesReceiptById() {
            Receipt receipt = service.delete(0);
            assertEquals(validReceipt.withId(0), receipt);
            assertEquals(0, service.list().size());
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError(){
            assertMissingFieldOnUpdate(validReceipt.withReceiptDate(null));
            assertMissingFieldOnUpdate(validReceipt.withReceiptCategory(null));
            assertMissingFieldOnUpdate(validReceipt.withAmount(null));
            assertMissingFieldOnUpdate(validReceipt.withVatRate(null));
            assertMissingFieldOnUpdate(validReceipt.withDescription(null));
        }

        private void assertMissingFieldOnUpdate(Receipt receipt) {
            assertThrows(MissingFieldError.class, () -> service.update(0, receipt));
        }
    }
}
