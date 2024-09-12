package com.sadesoft.portal.core.service;

import static com.sadesoft.portal.base.util.TestUtil.*;
import com.sadesoft.portal.core.query.GrantQuery;
import com.sadesoft.portal.core.mock.MockGrantRepo;
import com.sadesoft.portal.core.mock.MockPermissionRepo;
import com.sadesoft.portal.core.mock.MockRoleRepo;
import com.sadesoft.portal.core.model.Grant;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.model.Permission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GrantServiceTest {
    private MockGrantRepo grantRepo;
    private MockRoleRepo roleRepo;
    private MockPermissionRepo permissionRepo;
    private GrantService service;
    private Grant validGrant;

    @BeforeEach
    public void setup() {
        roleRepo = new MockRoleRepo();
        permissionRepo = new MockPermissionRepo();
        grantRepo = new MockGrantRepo(roleRepo, permissionRepo);
        service = new GrantService(grantRepo);
        validGrant = new Grant(null, "0", "0");
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingField(() -> service.create(validGrant.withRoleId(null)));
        assertMissingField(() -> service.create(validGrant.withPermissionId(null)));
    }

    @Test
    public void whenHasInvalidInputOnCreate_throwsError() throws Exception {
        assertIdField("roleId");
        assertIdField("permissionId");
    }

    private void assertIdField(String fieldName) throws Exception {
        assertInvalidInput(() -> service.create(withField(validGrant, fieldName, "A")));
        assertInvalidInput(() -> service.create(withField(validGrant, fieldName, "")));
        assertDoesNotThrow(() -> service.create(withField(validGrant, fieldName, "11")));
    }

    private Grant withField(Grant grant, String fieldName, String value) throws Exception {
        Field field = grant.getClass().getDeclaredField(fieldName);
        field.set(grant, value);
        return grant;
    }

    @Nested
    public class GivenServiceWithOneGrant {
        @BeforeEach
        public void setup() {
            grantRepo.create(validGrant);
        }

        @Test
        public void listsGrants() {
            List<Grant> grants = service.listBy(new GrantQuery());
            assertEquals(1, grants.size());
            assertEquals(validGrant.withId("0"), grants.get(0));
        }

        @Test
        public void listsAfterGivenId() {
            grantRepo.create(validGrant.copy());
            assertEquals(2, service.listBy(new GrantQuery().withOffset(0)).size());
            assertEquals(1, service.listBy(new GrantQuery().withOffset(1)).size());
            assertEquals(0, service.listBy(new GrantQuery().withOffset(2)).size());
        }

        @Test
        public void listsDetailedGrantsByRoleId() {
            roleRepo.create(new Role());
            permissionRepo.create(new Permission());
            assertEquals(1, service.listDetailedBy(new GrantQuery().withRoleId("0")).size());
            assertEquals(0, service.listDetailedBy(new GrantQuery().withRoleId("1")).size());
        }

        @Test
        public void listsDetailedGrantsByUserId() {
            roleRepo.create(new Role());
            permissionRepo.create(new Permission());
            assertEquals(1, service.listDetailedBy(new GrantQuery().withPermissionId("0")).size());
            assertEquals(0, service.listDetailedBy(new GrantQuery().withPermissionId("1")).size());
        }

        @Test
        public void listsDetailedAfterGivenId() {
            roleRepo.create(new Role());
            permissionRepo.create(new Permission());
            grantRepo.create(validGrant.copy());
            assertEquals(2, service.listDetailedBy(new GrantQuery().withOffset(0)).size());
            assertEquals(1, service.listDetailedBy(new GrantQuery().withOffset(1)).size());
            assertEquals(0, service.listDetailedBy(new GrantQuery().withOffset(2)).size());
        }

        @Test
        public void deletesGrantById() {
            Grant grant = service.delete("0");
            assertEquals(validGrant.withId("0"), grant);
            assertEquals(0, grantRepo.list().size());
        }
    }
}
