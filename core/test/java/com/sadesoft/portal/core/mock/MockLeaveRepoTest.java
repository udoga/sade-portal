package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.core.model.ApprovalStatus;
import com.sadesoft.portal.core.model.Leave;
import com.sadesoft.portal.core.repo.LeaveRepo;
import com.sadesoft.portal.core.sample.LeaveSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockLeaveRepoTest {
    LeaveRepo leaveRepo;
    Leave validLeave;

    @BeforeEach
    public void setup() {
        leaveRepo = new MockLeaveRepo();
        validLeave = LeaveSamples.validLeave.copy();
    }

    @Test
    public void whenLeaveStatusIsNull_doesNotFilter() {
        leaveRepo.create(validLeave.withStatus(ApprovalStatus.Waiting));
        assertEquals(leaveRepo.list(), leaveRepo.paginateByStatus(1, 5, null));
    }

    @Test
    public void whenThereIsOneLeave_filtersByStatus() {
        leaveRepo.create(validLeave.withStatus(ApprovalStatus.Approved));
        assertEquals(leaveRepo.list(), leaveRepo.paginateByStatus(1, 5, ApprovalStatus.Approved));
        assertEquals(new ArrayList<Leave>(), leaveRepo.paginateByStatus(1, 5, ApprovalStatus.Rejected));
    }

    @Test
    public void whenTwoLeavesHaveDifferentStatus_filtersByStatus() {
        leaveRepo.create(validLeave.withStatus(ApprovalStatus.Rejected));
        leaveRepo.create(validLeave.withStatus(ApprovalStatus.Approved));
        List<Leave> filteredLeaves = leaveRepo.paginateByStatus(1, 5, ApprovalStatus.Rejected);
        assertEquals(1, filteredLeaves.size());
        assertEquals(ApprovalStatus.Rejected, filteredLeaves.get(0).status);
    }
}


