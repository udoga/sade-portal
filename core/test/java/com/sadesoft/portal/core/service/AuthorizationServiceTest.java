package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.AuthorizationError;
import com.sadesoft.portal.core.mock.MockPermissionRepo;
import com.sadesoft.portal.core.model.Permission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationServiceTest {
    private MockPermissionRepo repo;
    private AuthorizationService service;

    @BeforeEach
    public void setup() {
        repo = new MockPermissionRepo();
        service = new AuthorizationService(true, repo);
        repo.authorizedUserId = "1";
    }

    @Test
    public void whenThereIsNoPermissions_throwsAuthorizationError() {
        assertUnauthorized("read_user", () -> service.authorize("1", "read_user"));
        assertUnauthorized("create_user", () -> service.authorize("1", "create_user"));
    }

    @Test
    public void whenAuthorizationIsDisabled_doesNotThrowError() {
        assertDoesNotThrow(() -> new AuthorizationService(false, repo).authorize("1", "read_user"));
    }

    private void assertUnauthorized(String requestName, Executable executable) {
        AuthorizationError e = assertThrows(AuthorizationError.class, executable);
        assertEquals(requestName, e.requestName);
    }

    @Nested
    public class GivenAuthorizerServiceWithSinglePermission {
        @BeforeEach
        public void setup() {
            repo.create(new Permission(null, "sp-read_user"));
        }

        @Test
        public void whenContainsPermission_doesNotThrowError() {
            assertDoesNotThrow(() -> new AuthorizationService(false, repo).authorize("1", "read_user"));
            assertDoesNotThrow(() -> new AuthorizationService(false, repo).authorize("1", "read_user:own"));
            assertUnauthorized("create_user", () -> service.authorize("1", "create_user"));
        }

        @Test
        public void whenCallerIdIsNull_throwsAuthorizationError() {
            assertUnauthorized("read_user", () -> service.authorize(null, "read_user"));
        }

        @Test
        public void whenCallerIdIsUnauthorized_throwsAuthenticationError() {
            repo.authorizedUserId = "2";
            assertUnauthorized("read_user", () -> service.authorize("1", "read_user"));
        }
    }
}
