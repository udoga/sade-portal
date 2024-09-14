package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.AuthorizationError;
import com.sadesoft.portal.core.mock.MockPermissionRepo;
import com.sadesoft.portal.core.model.Permission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationServiceTest {
    private MockPermissionRepo repo;
    private AuthorizationService service;

    @BeforeEach
    public void setup() {
        repo = new MockPermissionRepo();
        service = new AuthorizationService(repo, true, "");
        repo.authorizedUserId = "1";
    }

    @Test
    public void whenThereIsNoPermissions_throwsAuthorizationError() {
        assertUnauthorized("user_read", () -> service.authorize("1", "user_read", List.of()));
        assertUnauthorized("user_create", () -> service.authorize("1", "user_create", List.of()));
    }

    @Test
    public void whenAuthorizationIsDisabled_doesNotThrowError() {
        assertDoesNotThrow(() -> new AuthorizationService(repo, false, "").authorize("1", "user_read", List.of()));
    }

    @Test
    public void whenPrefixIsSet_addsItToPermissionNames() {
        repo.create(new Permission(null, "sp-user_read"));
        assertDoesNotThrow(() -> new AuthorizationService(repo, true, "sp-").authorize("1", "user_read", List.of()));
    }

    private void assertUnauthorized(String permissionName, Executable executable) {
        AuthorizationError e = assertThrows(AuthorizationError.class, executable);
        assertEquals(permissionName, e.permissionName);
    }

    @Nested
    public class GivenAuthorizerServiceWithSinglePermission {
        @BeforeEach
        public void setup() {
            repo.create(new Permission(null, "user_read"));
        }

        @Test
        public void whenCallerIdIsNull_throwsAuthorizationError() {
            assertUnauthorized("user_read", () -> service.authorize(null, "user_read", List.of()));
        }

        @Test
        public void whenCallerIdIsUnauthorized_throwsAuthenticationError() {
            repo.authorizedUserId = "2";
            assertUnauthorized("user_read", () -> service.authorize("1", "user_read", List.of()));
        }

        @Test
        public void whenContainsPermission_doesNotThrowError() {
            assertDoesNotThrow(() -> service.authorize("1", "user_read", List.of()));
            assertUnauthorized("user_create", () -> service.authorize("1", "user_create", List.of()));
        }

        @Test
        public void whenThereIsSingleFlag_checksOneAdditionalPermission() {
            assertUnauthorized("user_read-a", () -> service.authorize("1", "user_read", List.of("a")));
        }

        @Test
        public void whenThereAreTwoFlags_checksTwoAdditionalPermissions() {
            repo.create(new Permission(null, "user_read-a"));
            assertUnauthorized("user_read-b", () -> service.authorize("1", "user_read", List.of("a", "b")));
        }
    }
}
