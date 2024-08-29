package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.query.AssignmentQuery;
import com.sadesoft.portal.core.mock.MockAssignmentRepo;
import com.sadesoft.portal.core.mock.MockRoleRepo;
import com.sadesoft.portal.core.mock.MockUserRepo;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssignmentServiceTest {
    private MockRoleRepo roleRepo;
    private MockUserRepo userRepo;
    private MockAssignmentRepo assignmentRepo;
    private AssignmentService service;
    private Assignment validAssignment;

    @BeforeEach
    public void setup() {
        roleRepo = new MockRoleRepo();
        userRepo = new MockUserRepo();
        assignmentRepo = new MockAssignmentRepo(roleRepo, userRepo);
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
        assertEquals(assignmentRepo.list(), service.listBy(new AssignmentQuery()));
    }

    @Test
    public void listsDetailedAssignmentsByUserId() {
        roleRepo.create(new Role());
        userRepo.create(new User());
        assignmentRepo.create(validAssignment);
        assertEquals(1, service.listDetailedBy(new AssignmentQuery().withUserId("0")).size());
        assertEquals(0, service.listDetailedBy(new AssignmentQuery().withUserId("1")).size());
    }

    @Test
    public void listsDetailedAssignmentsByRoleId() {
        userRepo.create(new User());
        roleRepo.create(new Role());
        assignmentRepo.create(validAssignment);
        assertEquals(1, service.listDetailedBy(new AssignmentQuery().withRoleId("0")).size());
        assertEquals(0, service.listDetailedBy(new AssignmentQuery().withRoleId("1")).size());
    }

    @Test
    public void deletesAssignmentById() {
        service.create(validAssignment);
        Assignment assignment = service.delete("0");
        assertEquals(validAssignment.withId("0"), assignment);
        assertEquals(0, assignmentRepo.list().size());
    }
}
