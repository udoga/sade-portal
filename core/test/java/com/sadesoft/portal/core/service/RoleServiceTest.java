package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockRoleRepo;
import com.sadesoft.portal.core.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleServiceTest {
    private MockRoleRepo repo;
    private RoleService service;
    private Role validRole;

    @BeforeEach
    public void setup() {
        repo = new MockRoleRepo();
        service = new RoleService(repo);
        validRole = new Role(null, "admin");
    }

    @Test
    public void createsRole() {
        Role role = service.create(validRole);
        assertEquals(validRole.withId("0"), role);
        assertEquals(1, repo.list().size());
        assertEquals(role, repo.read("0"));
    }

    @Nested
    public class GivenServiceWithOneRole {
        @BeforeEach
        public void setup() {
            repo.create(validRole);
        }

        @Test
        public void listsRoles() {
            assertEquals(1, service.list().size());
            assertEquals(validRole.withId("0"), service.list().get(0));
        }

        @Test
        public void listsRoleOptions() {
            assertEquals(repo.listOptions(), service.listOptions());
        }

        @Test
        public void readsRoles() {
            assertEquals(validRole.withId("0"), service.read("0"));
        }

        @Test
        public void updatesRoleById() {
            Role role = service.update("0", validRole.withName("updated"));
            assertEquals(validRole.withId("0").withName("updated"), role);
            assertEquals(role, service.read("0"));
        }

        @Test
        public void deletesRoleById() {
            Role role = service.delete("0");
            assertEquals(validRole.withId("0"), role);
            assertEquals(0, service.list().size());
        }
    }
}
