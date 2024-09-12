package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.auth.AuthConfig;
import com.sadesoft.portal.base.error.SessionError;
import com.sadesoft.portal.core.mock.MockSessionRepo;
import com.sadesoft.portal.core.mock.MockUserRepo;
import com.sadesoft.portal.core.model.Session;
import com.sadesoft.portal.core.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class SessionServiceTest {
    private AuthConfig config;
    private MockUserRepo userRepo;
    private MockSessionRepo sessionRepo;
    private SessionService service;
    private User firstUser;
    private User secondUser;

    private void assertTimeEquals(LocalDateTime expected, LocalDateTime actual) {
        assertTrue(Math.abs(ChronoUnit.SECONDS.between(expected, actual)) <= 1);
    }

    private void assertInvalidCredentials(User user) {
        assertThrows(SessionError.class, () -> service.login(user));
    }

    private LocalDateTime minutesLater(int minutes) {
        return LocalDateTime.now().plusMinutes(minutes);
    }

    @BeforeEach
    public void setup() {
        config = new AuthConfig(true, true, 3600, "salt");
        userRepo = new MockUserRepo();
        sessionRepo = new MockSessionRepo();
        service = new SessionService(config, sessionRepo, userRepo);
        firstUser = new User(null, "first-username", "first-password");
        secondUser = new User(null, "second-username", "second-password");
    }

    @Test
    void whenThereAreNoSessions_listedSessionsAreEmpty() {
        assertEquals(0, service.list().size());
    }

    @Test
    void whenThereAreNoSessions_doesNotAuthenticateToken() {
        SessionError e = assertThrows(SessionError.class, () -> service.authenticate("sample-token"));
        assertEquals("Session not found, invalid token.", e.getMessage());
    }

    @Test
    public void whenThereAreNoUsers_doesNotCreateSession() {
        SessionError e = assertThrows(SessionError.class, () -> service.login(firstUser));
        assertEquals("Invalid username or password.", e.getMessage());
    }

    @Test
    public void whenAuthenticationIsDisabled_doesNotThrowError() {
        config.authenticationEnabled = false;
        assertEquals(new Session(), service.authenticate("sample-token"));
    }

    @Test
    public void whenReceivesLogoutRequest_throwsError() {
        var e =  assertThrows(SessionError.class, () -> service.logout(""));
        assertEquals("Session not found, invalid token.", e.getMessage());
    }

    @Nested
    public class GivenSessionServiceWithOneSession {
        private Session session;

        @BeforeEach
        public void setup() {
            session = sessionRepo.create(new Session(
                    null,
                    "1",
                    LocalDateTime.of(2022, 1, 1, 10, 0),
                    LocalDateTime.of(2022, 1, 1, 12, 0),
                    "sample-token"));
        }

        @Test
        public void readsSessionWithoutToken() {
            assertEquals(session.withToken(null), service.read("0"));
        }

        @Test
        public void listsSessionsWithoutTokens() {
            assertEquals(1, service.list().size());
            assertNull(service.list().get(0).token);
        }

        @Test
        public void whenReceivesLogoutRequest_updatesEndTime() {
            service.logout(session.token);
            assertTimeEquals(LocalDateTime.now(), sessionRepo.read("0").endTime);
        }
    }

    @Nested
    public class GivenSessionServiceWithTwoUsers {
        @BeforeEach
        public void setup() {
            userRepo.create(firstUser.withEncryptedPassword(config.salt));
            userRepo.create(secondUser.withEncryptedPassword(config.salt));
        }

        @Test
        public void whenUsersTriesToLoginWithIncorrectUsername_doesNotCreateSession() {
            assertInvalidCredentials(firstUser.withUsername(""));
            assertInvalidCredentials(firstUser.withUsername("x"));
            assertInvalidCredentials(secondUser.withUsername("x"));
        }

        @Test
        public void whenUsersTriesToLoginWithIncorrectPassword_doesNotCreateSession() {
            assertInvalidCredentials(firstUser.withPassword(""));
            assertInvalidCredentials(firstUser.withPassword("x"));
            assertInvalidCredentials(secondUser.withPassword("x"));
        }

        @Test
        public void whenFirstUserTriesToLoginWithCorrectCredentials_createsSession() {
            Session session = service.login(firstUser);
            assertEquals(1, sessionRepo.list().size());
            assertEquals(session, sessionRepo.read("0"));
            assertEquals("0", session.userId);
            assertTimeEquals(LocalDateTime.now(), session.beginTime);
            assertTimeEquals(LocalDateTime.now().plusHours(1), session.endTime);
            assertEquals(36, session.token.length());
        }

        @Test
        public void whenSecondUserTriesToLoginWithCorrectCredentials_createsSession() {
            Session session = service.login(secondUser);
            assertEquals(1, sessionRepo.list().size());
            assertEquals("1", session.userId);
        }

        @Test
        public void whenSessionTimeoutIsSetDifferent_savesDifferentEndTime() {
            config.sessionTimeout = 30;
            Session session = service.login(firstUser);
            assertEquals(1, sessionRepo.list().size());
            assertTimeEquals(LocalDateTime.now().plusSeconds(30), session.endTime);
        }

        @Nested
        public class GivenSessionServiceWithOneLoggedInUser {
            private Session session;

            @BeforeEach
            public void setup() {
                session = service.login(firstUser);
            }

            @Test
            public void whenTokenIsNull_requestsLogin() {
                var e = assertThrows(SessionError.class, () -> service.authenticate(null));
                assertEquals("Please login to continue.", e.getMessage());
            }

            @Test
            public void checksEqualityWhileAuthentication() {
                assertEquals(sessionRepo.read("0"), service.authenticate(session.token));
                var e = assertThrows(SessionError.class, () -> service.authenticate("another-token"));
                assertEquals("Session not found, invalid token.", e.getMessage());
            }

            @Test
            public void whenThereAreTwoSessions_authenticatesIfTokenExists() {
                Session secondSession = service.login(secondUser);
                assertEquals(sessionRepo.read("0"), service.authenticate(session.token));
                assertEquals(sessionRepo.read("1"), service.authenticate(secondSession.token));
                assertThrows(SessionError.class, () -> service.authenticate("missing-token"));
            }

            @Test
            public void whenSessionIsExpired_doesNotAuthenticateToken() {
                assertDoesNotThrow(() -> service.authenticate(session.token, minutesLater(59)));
                var e = assertThrows(SessionError.class, () -> service.authenticate(session.token, minutesLater(61)));
                assertEquals("Session expired, please login.", e.getMessage());
            }

            @Test
            public void whenSessionIsNotStarted_doesNotAuthenticateToken() {
                var e = assertThrows(SessionError.class, () -> service.authenticate(session.token, minutesLater(-1)));
                assertEquals("Session is not started yet.", e.getMessage());
            }
        }
    }
}
