package com.sadesoft.portal.core.service;

import com.sadesoft.portal.base.error.InvalidCredentialsError;
import com.sadesoft.portal.core.mock.MockSessionRepo;
import com.sadesoft.portal.core.mock.MockUserRepo;
import com.sadesoft.portal.core.model.Session;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.sample.UserSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

public class SessionServiceTest {
    private MockUserRepo userRepo;
    private MockSessionRepo sessionRepo;
    private SessionService service;
    private User firstUser;
    private User secondUser;

    private void assertTimeEquals(LocalDateTime expected, LocalDateTime actual) {
        assertTrue(ChronoUnit.SECONDS.between(expected, actual) <= 1);
    }

    private void assertInvalidCredentials(User user) {
        assertThrows(InvalidCredentialsError.class, () -> service.login(user));
    }

    @BeforeEach
    public void setup() {
        userRepo = new MockUserRepo();
        sessionRepo = new MockSessionRepo();
        service = new SessionService(sessionRepo, userRepo);
        firstUser = UserSamples.firstUser.copy();
        secondUser = UserSamples.secondUser.copy();
    }

    @Test
    public void whenUserIsNotSavedOnRepo_doesNotCreateSession() {
        assertThrows(InvalidCredentialsError.class, () -> service.login(firstUser));
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
    }
}
