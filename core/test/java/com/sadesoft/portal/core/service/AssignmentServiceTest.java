package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.RecordNotFoundError;
import com.sadesoft.portal.core.mock.MockAssignmentRepo;
import com.sadesoft.portal.core.mock.MockRoleRepo;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssignmentServiceTest {
    private MockRoleRepo roleRepo;
    private MockAssignmentRepo assignmentRepo;
    private AssignmentService service;
    private Assignment validAssignment;

    @BeforeEach
    public void setup() {
        roleRepo = new MockRoleRepo();
        assignmentRepo = new MockAssignmentRepo(roleRepo);
        service = new AssignmentService(assignmentRepo);
        validAssignment = new Assignment(null, "0", "0");
    }

    @Test
    public void createsAssignment() {
        Assignment assignment = service.create(validAssignment);
        assertEquals(1, assignmentRepo.list().size());
        assertEquals(assignment, assignmentRepo.read("0"));
    }

    @Test
    public void listsAssignments() {
        service.create(validAssignment);
        assertEquals(assignmentRepo.list(), service.list());
    }

    @Test
    public void deletesAssignmentByValues() {
        service.create(validAssignment);
        service.deleteByValues(validAssignment);
        assertEquals(0, assignmentRepo.list().size());
    }

    @Test
    public void listsAssignmentsWithRoleByUserId() {
        roleRepo.create(new Role("0", "admin"));
        assignmentRepo.create(validAssignment);
        assertEquals(1, service.listWithRoleByUserId(null).size());
        assertEquals(1, service.listWithRoleByUserId("0").size());
        assertEquals(assignmentRepo.listWithRole(), service.listWithRoleByUserId("0"));
    }

    @Test
    public void whenAssignmentNotFoundWhileDeleting_throwsError() {
        assertThrows(RecordNotFoundError.class, () -> service.deleteByValues(validAssignment));
    }
}
