package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.RecordNotFoundError;
import com.sadesoft.portal.core.mock.MockAssignmentRepo;
import com.sadesoft.portal.core.model.Assignment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssignmentServiceTest {
    private MockAssignmentRepo repo;
    private AssignmentService service;
    private Assignment validAssignment;

    @BeforeEach
    public void setup() {
        repo = new MockAssignmentRepo();
        service = new AssignmentService(repo);
        validAssignment = new Assignment(null, "0", "0");
    }

    @Test
    public void createsAssignment() {
        Assignment assignment = service.create(validAssignment);
        assertEquals(1, repo.list().size());
        assertEquals(assignment, repo.read("0"));
    }

    @Test
    public void listsAssignments() {
        service.create(validAssignment);
        assertEquals(repo.list(), service.list());
    }

    @Test
    public void deletesAssignmentByValues() {
        service.create(validAssignment);
        service.deleteByValues(validAssignment);
        assertEquals(0, repo.list().size());
    }

    @Test
    public void whenAssignmentNotFoundWhileDeleting_throwsError() {
        assertThrows(RecordNotFoundError.class, () -> service.deleteByValues(validAssignment));
    }
}
