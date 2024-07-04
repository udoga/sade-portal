package com.sadesoft.portal.core.mock;

import com.sadesoft.portal.core.detailed.DetailedAssignment;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.repo.AssignmentRepo;
import com.sadesoft.portal.core.repo.RoleRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MockAssignmentRepoTest {
    private AssignmentRepo assignmentRepo;
    private Assignment assignment;

    @BeforeEach
    public void setup() {
        assignmentRepo = new MockAssignmentRepo(null);
        assignment = new Assignment(null, "0", "0");
    }

    @Test
    void whenRoleIsNotFound_doesNotInitializeRole() {
        assignmentRepo.create(assignment);
        List<DetailedAssignment> assignments = assignmentRepo.listWithRole();
        assertEquals(assignment, assignments.get(0));
        assertNull(assignments.get(0).role);
    }

    @Nested
    public class GivenTwoAssignmentsAndTwoRoles {
        private RoleRepo roleRepo;

        @BeforeEach
        public void setup() {
            roleRepo = new MockRoleRepo();
            assignmentRepo = new MockAssignmentRepo(roleRepo);
            roleRepo.create(new Role("0", "admin"));
            roleRepo.create(new Role("1", "user"));
            assignmentRepo.create(assignment.withRoleId("1"));
            assignmentRepo.create(assignment.withRoleId("0"));
        }

        @Test
        void whenListsWithRoles_initializesAssignmentsWithRole() {
            List<DetailedAssignment> assignments = assignmentRepo.listWithRole();
            assertEquals(roleRepo.read("1"), assignments.get(0).role);
            assertEquals(roleRepo.read("0"), assignments.get(1).role);
        }

        @Test
        void whenListsWithRoles_filtersByUserId() {
            assignmentRepo.create(assignment.withUserId("1"));
            assertEquals(2, assignmentRepo.listWithRoleByUserId("0").size());
            assertEquals(1, assignmentRepo.listWithRoleByUserId("1").size());
        }
    }
}
