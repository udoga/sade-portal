package com.sadesoft.portal.core.service;

import static com.sadesoft.portal.base.util.TestUtil.*;
import com.sadesoft.portal.core.query.AssignmentQuery;
import com.sadesoft.portal.core.mock.MockAssignmentRepo;
import com.sadesoft.portal.core.mock.MockRoleRepo;
import com.sadesoft.portal.core.mock.MockUserRepo;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingField(() -> service.create(validAssignment.withUserId(null)));
        assertMissingField(() -> service.create(validAssignment.withRoleId(null)));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() throws Exception {
        assertIdField("userId");
        assertIdField("roleId");
    }

    private void assertIdField(String fieldName) throws Exception {
        assertInvalidInput(() -> service.create(withField(validAssignment, fieldName, "A")));
        assertInvalidInput(() -> service.create(withField(validAssignment, fieldName, "")));
        assertDoesNotThrow(() -> service.create(withField(validAssignment, fieldName, "11")));
    }

    private Assignment withField(Assignment assignment, String fieldName, String value) throws Exception {
        Field field = assignment.getClass().getDeclaredField(fieldName);
        field.set(assignment, value);
        return assignment;
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
    public void readsAssignment() {
        assignmentRepo.create(validAssignment);
        assertEquals(validAssignment.withId("0"), service.read("0"));
    }

    @Test
    public void updatesAssignment() {
        assignmentRepo.create(validAssignment);
        Assignment assignment = service.update("0", validAssignment.withRoleId("1"));
        assertEquals(validAssignment.withId("0").withRoleId("1"), assignment);
        assertEquals(assignment, assignmentRepo.read("0"));
    }

    @Test
    public void deletesAssignmentById() {
        service.create(validAssignment);
        Assignment assignment = service.delete("0");
        assertEquals(validAssignment.withId("0"), assignment);
        assertEquals(0, assignmentRepo.list().size());
    }

    @Test
    public void whenHasMissingFieldOnUpdate_throwsError() {
        assignmentRepo.create(validAssignment);
        assertMissingField(() -> service.update("0", validAssignment.withUserId(null)));
        assertMissingField(() -> service.update("0", validAssignment.withRoleId(null)));
    }
}
