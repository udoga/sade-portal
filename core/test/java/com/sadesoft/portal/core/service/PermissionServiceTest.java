package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockPermissionRepo;
import com.sadesoft.portal.core.model.Permission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PermissionServiceTest {
    private MockPermissionRepo repo;
    private PermissionService service;
    private Permission validPermission;

    @BeforeEach
    public void setup() {
        repo = new MockPermissionRepo();
        service = new PermissionService(repo);
        validPermission = new Permission(null, "user_create");
    }

    @Nested
    public class GivenServiceWithOnePermission {
        @BeforeEach
        public void setup() {
            repo.create(validPermission);
        }

        @Test
        public void listsPermissions() {
            assertEquals(1, service.list().size());
            assertEquals(validPermission.withId("0"), service.list().get(0));
        }

        @Test
        public void listsSummarized() {
            repo.create(validPermission.withName("employee_create"));
            assertEquals(2, service.listSummarized().size());
            assertEquals("employee_create", service.listSummarized().get(0).name);
            assertEquals("user_create", service.listSummarized().get(1).name);
        }

        @Test
        public void readsPermissions() {
            assertEquals(validPermission.withId("0"), service.read("0"));
        }
    }
}
