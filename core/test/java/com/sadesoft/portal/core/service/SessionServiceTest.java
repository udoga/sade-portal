package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.config.Props;
import com.sadesoft.portal.base.config.ServerConfig;
import com.sadesoft.portal.base.error.SessionError;
import com.sadesoft.portal.core.mock.MockSessionRepo;
import com.sadesoft.portal.core.mock.MockUserRepo;
import com.sadesoft.portal.core.model.Session;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.sample.SessionSamples;
import com.sadesoft.portal.core.sample.UserSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SessionServiceTest {
    private MockUserRepo userRepo;
    private MockSessionRepo sessionRepo;
    private SessionService service;
    private User firstUser;
    private User secondUser;
    private ServerConfig serverConfig;

    private void assertTimeEquals(LocalDateTime expected, LocalDateTime actual) {
        assertTrue(ChronoUnit.SECONDS.between(expected, actual) <= 1);
    }

    private void assertInvalidCredentials(User user) {
        assertThrows(SessionError.class, () -> service.login(user));
    }

    private LocalDateTime minutesLater(int minutes) {
        return LocalDateTime.now().plusMinutes(minutes);
    }

    @BeforeEach
    public void setup() {
        Props props = new Props(Map.of("session_timeout", "3600", "authentication_enabled", "true"));
        serverConfig = new ServerConfig(props);
        userRepo = new MockUserRepo();
        sessionRepo = new MockSessionRepo();
        service = new SessionService(serverConfig, sessionRepo, userRepo);
        firstUser = UserSamples.firstUser.copy();
        secondUser = UserSamples.secondUser.copy();
    }

    @Test
    void whenThereAreNoSessions_listedSessionsAreEmpty() {
        assertEquals(0, service.list().size());
    }

    @Test
    void whenThereAreNoSessions_doesNotAuthenticateToken() {
        SessionError e = assertThrows(SessionError.class, () -> service.authenticate("sample-token"));
        assertEquals("Session not found.", e.getMessage());
    }

    @Test
    public void whenThereAreNoUsers_doesNotCreateSession() {
        SessionError e = assertThrows(SessionError.class, () -> service.login(firstUser));
        assertEquals("Invalid username or password.", e.getMessage());
    }

    @Test
    public void whenAuthenticationIsDisabled_doesNotAuthenticate() {
        serverConfig.authenticationEnabled = false;
        assertDoesNotThrow(() -> service.authenticate("sample-token"));
    }

    @Nested
    public class GivenSessionServiceWithOneSession {
        private Session session;

        @BeforeEach
        public void setup() {
            session = sessionRepo.create(SessionSamples.validSession.copy());
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
    }

    @Nested
    public class GivenSessionServiceWithTwoUsers {
        @BeforeEach
        public void setup() {
            userRepo.create(firstUser);
            userRepo.create(secondUser);
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
            assertEquals(firstUser.id, session.userId);
            assertTimeEquals(LocalDateTime.now(), session.beginTime);
            assertTimeEquals(LocalDateTime.now().plusHours(1), session.endTime);
            assertEquals(36, session.token.length());
        }

        @Test
        public void whenSecondUserTriesToLoginWithCorrectCredentials_createsSession() {
            Session session = service.login(secondUser);
            assertEquals(1, sessionRepo.list().size());
            assertEquals(secondUser.id, session.userId);
        }

        @Test
        public void whenSessionTimeoutIsSetDifferent_savesDifferentEndTime() {
            serverConfig.sessionTimeout = 30;
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
                assertDoesNotThrow(() -> service.authenticate(session.token));
                var e = assertThrows(SessionError.class, () -> service.authenticate("another-token"));
                assertEquals("Session not found.", e.getMessage());
            }

            @Test
            public void whenThereAreTwoSessions_authenticatesIfTokenExists() {
                Session secondSession = service.login(secondUser);
                assertDoesNotThrow(() -> service.authenticate(session.token));
                assertDoesNotThrow(() -> service.authenticate(secondSession.token));
                assertThrows(SessionError.class, () -> service.authenticate("missing-token"));
            }

            @Test
            public void whenSessionIsExpired_doesNotAuthenticateToken() {
                assertDoesNotThrow(() -> service.authenticate(session.token, minutesLater(59)));
                var e = assertThrows(SessionError.class, () -> service.authenticate(session.token, minutesLater(61)));
                assertEquals("Session is expired.", e.getMessage());
            }

            @Test
            public void whenSessionIsNotStarted_doesNotAuthenticateToken() {
                var e = assertThrows(SessionError.class, () -> service.authenticate(session.token, minutesLater(-1)));
                assertEquals("Session is not started.", e.getMessage());
            }
        }
    }
}
