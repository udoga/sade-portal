package com.sadesoft.portal.core.service;

import com.sadesoft.portal.core.mock.MockSessionRepo;
import com.sadesoft.portal.core.model.Session;
import com.sadesoft.portal.core.sample.SessionSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SessionServiceTest {
    private MockSessionRepo repo;
    private SessionService service;
    private Session validSession;

    @BeforeEach
    public void setup() {
        repo = new MockSessionRepo();
        service = new SessionService(repo);
        validSession = SessionSamples.validSession.copy();
    }

    @Nested
    public class GivenServiceWithOneSession {
        @BeforeEach
        public void setup() {
            repo.create(validSession);
        }

        @Test
        public void listsSessions() {
            assertEquals(1, service.list().size());
            assertEquals(validSession, service.list().get(0));
            assertEquals(validSession, service.read("0"));
        }
    }
}
