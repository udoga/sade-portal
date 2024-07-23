package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockAssignmentRepo;
import com.sadesoft.portal.core.mock.MockRoleRepo;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(assignmentRepo.list(), service.listBy(null));
    }

    @Test
    public void deletesAssignmentById() {
        service.create(validAssignment);
        Assignment assignment = service.delete("0");
        assertEquals(validAssignment.withId("0"), assignment);
        assertEquals(0, assignmentRepo.list().size());
    }

    @Test
    public void listsDetailedAssignmentsByUserId() {
        roleRepo.create(new Role());
        assignmentRepo.create(validAssignment);
        assertEquals(assignmentRepo.listDetailedBy("0"), service.listDetailedBy("0"));
        assertEquals(assignmentRepo.listDetailedBy("1"), service.listDetailedBy("1"));
    }
}
