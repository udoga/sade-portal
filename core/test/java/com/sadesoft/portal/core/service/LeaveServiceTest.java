package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.ClientError;
import com.sadesoft.portal.base.error.EmptyFieldError;
import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.mock.MockLeaveRepo;
import com.sadesoft.portal.core.model.ApprovalStatus;
import com.sadesoft.portal.core.model.Leave;
import com.sadesoft.portal.core.sample.LeaveSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LeaveServiceTest {
    private MockLeaveRepo repo;
    private LeaveService service;
    private Leave validLeave;

    private void assertEmptyField(Leave leave, String field) {
        EmptyFieldError e = assertThrows(EmptyFieldError.class, () -> service.create(leave));
        assertEquals("Field '" + field + "' should not be empty.", e.getMessage());
    }

    private void assertMissingField(Leave leave) {
        assertThrows(MissingFieldError.class, () -> service.create(leave));
    }

    private void assertForbiddenField(Leave leave) {
        assertThrows(ClientError.class, () -> service.create(leave));
    }

    @BeforeEach
    public void setup() {
        repo = new MockLeaveRepo();
        service = new LeaveService(repo);
        validLeave = LeaveSamples.validLeave.copy();
    }

    @Test
    public void createsLeaves() {
        Leave leave = service.create(validLeave);
        assertEquals(validLeave.withId("0").withStatus(ApprovalStatus.Waiting), leave);
        assertEquals(1, repo.list().size());
        assertEquals(leave, repo.read("0"));
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingField(validLeave.withPersonId(null));
        assertMissingField(validLeave.withLeaveType(null));
        assertMissingField(validLeave.withStartTime(null));
        assertMissingField(validLeave.withEndTime(null));
        assertMissingField(validLeave.withDayCount(null));
        assertMissingField(validLeave.withDescription(null));
    }

    @Test
    public void whenApprovalStatusIsFilled_throwsError() {
        assertForbiddenField(validLeave.withStatus(ApprovalStatus.Approved));
    }

    @Test
    public void whenDescriptionIsEmpty_throwsError() {
        assertEmptyField(validLeave.withDescription(""), "description");
    }

    @Nested
    public class GivenServiceWithOneLeave {
        @BeforeEach
        public void setup() {
            service.create(validLeave);
        }

        @Test
        public void listsLeaves() {
            assertEquals(1, service.list().size());
            assertEquals(repo.read("0"), service.list().get(0));
            assertEquals(repo.read("0"), service.read("0"));
        }

        @Test
        public void updatesLeaveById() {
            Leave leave = repo.read("0");
            Leave updatedLeave = service.update("0", validLeave.withDescription("updated"));
            assertEquals(leave.withDescription("updated"), updatedLeave);
            assertEquals(updatedLeave, repo.read("0"));
        }

        @Test
        public void whenUpdatesLeave_makesStatusWaiting() {
            repo.update("0", repo.read("0").withStatus(ApprovalStatus.Approved));
            Leave leave = service.update("0", validLeave);
            assertEquals(validLeave.withId("0").withStatus(ApprovalStatus.Waiting), leave);
        }

        @Test
        public void deletesLeaveById() {
            Leave firstLeave = repo.read("0");
            Leave deletedLeave = service.delete("0");
            assertEquals(firstLeave, deletedLeave);
            assertEquals(0, service.list().size());
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError(){
            assertThrows(MissingFieldError.class, () -> service.update("0", validLeave.withPersonId(null)));
            assertThrows(MissingFieldError.class, () -> service.update("0", validLeave.withStartTime(null)));
        }
    }
}
