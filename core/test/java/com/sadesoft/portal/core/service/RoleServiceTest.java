package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockRoleRepo;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.sample.RoleSamples;
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
        validRole = RoleSamples.validRole.copy();
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
        public void readsRoles() {
            assertEquals(validRole.withId("0"), service.read("0"));
        }

        @Test
        public void listsRolesByUserId() {
            repo.addUserRole("1", repo.read("0"));
            assertEquals(service.list(), service.listByUserId(null));
            assertEquals(0, service.listByUserId("0").size());
            assertEquals(1, service.listByUserId("1").size());
            assertEquals(repo.read("0"), service.listByUserId("1").get(0));
        }
    }
}
