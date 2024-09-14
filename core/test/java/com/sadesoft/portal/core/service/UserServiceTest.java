package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.AuthorizationError;
import com.sadesoft.portal.base.auth.MockAuthorizer;
import com.sadesoft.portal.core.mock.MockUserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.sadesoft.portal.base.error.MissingFieldError;
import com.sadesoft.portal.core.model.User;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

public class UserServiceTest {
    private String callerId;
    private MockUserRepo userRepo;
    private MockAuthorizer authorizer;
    private UserService service;
    private User validUser;

    @BeforeEach
    public void setup() {
        callerId = "0";
        userRepo = new MockUserRepo();
        authorizer = new MockAuthorizer();
        service = new UserService(userRepo, authorizer, "");
        validUser = new User(null, "sample-username", "sample-password");
        setupAuthorizer();
    }

    private void setupAuthorizer() {
        authorizer.callerIds.add(callerId);
        authorizer.permissionNames.addAll(List.of(
                "user_list",
                "user_list_options",
                "user_create",
                "user_read",
                "user_read-user:other",
                "user_update",
                "user_delete"));
    }

    @Test
    public void createsUser() {
        User user = service.create(callerId, validUser);
        assertEquals(validUser.withId("0").withPassword(null), user);
        assertEquals(1, userRepo.list().size());
        assertEquals(user.username, userRepo.read("0").username);
    }

    @Test
    public void whenCreatesUser_encryptsPassword() {
        service.create(callerId, validUser);
        assertEquals(validUser.findEncryptedPassword(""), userRepo.read("0").password);
    }

    @Test
    public void whenCreatesUser_encryptsPasswordWithSalt() {
        new UserService(userRepo, authorizer, "salt").create(callerId, validUser);
        assertEquals(validUser.findEncryptedPassword("salt"), userRepo.read("0").password);
    }

    @Test
    public void whenHasMissingFieldOnCreate_throwsError() {
        assertMissingFieldOnCreate(validUser.withUsername(null));
        assertMissingFieldOnCreate(validUser.withPassword(null));
    }

    private void assertMissingFieldOnCreate(User user) {
        assertThrows(MissingFieldError.class, () -> service.create(callerId, user));
    }

    @Nested
    public class GivenServiceWithOneUser {
        @BeforeEach
        public void setup() {
            service.create(callerId, validUser);
        }

        @Test
        public void listsUsers() {
            User expectedUser = validUser.withId("0").withPassword(null);
            assertEquals(1, service.list(callerId).size());
            assertEquals(expectedUser, service.list(callerId).get(0));
        }

        @Test
        public void listsUserOptions() {
            assertEquals(1, service.listOptions(callerId).size());
            assertNull(service.listOptions(callerId).get(0).password);
        }

        @Test
        public void readsUser() {
            userRepo.create(validUser);
            assertEquals(validUser.withId("0").withPassword(null), service.read(callerId, "0"));
            assertEquals(validUser.withId("1").withPassword(null), service.read(callerId, "1"));
        }

        @Test
        public void updatesUserById() {
            User user = service.update(callerId, "0", validUser.withUsername("updated"));
            assertEquals(validUser.withId("0").withUsername("updated").withPassword(null), user);
        }

        @Test
        public void whenUpdatesUser_encryptsPassword() {
            User updatedUser = validUser.withPassword("updated");
            service.update(callerId, "0", updatedUser);
            assertEquals(updatedUser.findEncryptedPassword(""), userRepo.read("0").password);
        }

        @Test
        public void whenUpdatesUser_encryptsPasswordWithSalt() {
            User updatedUser = validUser.withPassword("updated");
            new UserService(userRepo, authorizer, "salt").update(callerId, "0", updatedUser);
            assertEquals(updatedUser.findEncryptedPassword("salt"), userRepo.read("0").password);
        }

        @Test
        public void deletesUserById() {
            User user = service.delete(callerId, "0");
            assertEquals(validUser.withId("0").withPassword(null), user);
            assertEquals(0, service.list(callerId).size());
        }

        @Test
        public void whenHasMissingFieldOnUpdate_throwsError() {
            assertMissingFieldOnUpdate(validUser.withUsername(null));
            assertMissingFieldOnUpdate(validUser.withPassword(null));
        }

        @Test
        public void whenCallerIsUnauthorized_throwsError() {
            authorizer.callerIds.clear();
            assertUnauthorized("user_list", () -> service.list(callerId));
            assertUnauthorized("user_list_options", () -> service.listOptions(callerId));
            assertUnauthorized("user_create", () -> service.create(callerId, validUser));
            assertUnauthorized("user_read", () -> service.read(callerId, "0"));
            assertUnauthorized("user_update", () -> service.update(callerId, "0", validUser));
            assertUnauthorized("user_delete", () -> service.delete(callerId, "0"));
        }

        @Test
        public void whenReadOtherPermissionIsMissing_cannotReadOtherUser() {
            userRepo.create(validUser);
            authorizer.permissionNames.remove("user_read-user:other");
            assertDoesNotThrow(() -> service.read(callerId, "0"));
            assertUnauthorized("user_read-user:other", () -> service.read(callerId, "1"));
        }

        private void assertUnauthorized(String permissionName, Executable executable) {
            AuthorizationError e = assertThrows(AuthorizationError.class, executable);
            assertEquals(permissionName, e.permissionName);
        }

        private void assertMissingFieldOnUpdate(User user) {
            assertThrows(MissingFieldError.class, () -> service.update(callerId, "0", user));
        }
    }
}
