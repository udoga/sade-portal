package com.sadesoft.portal.core.model;

import com.sadesoft.portal.core.sample.SessionSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {
    private Session validSession;

    @BeforeEach
    public void setup() {
        validSession = SessionSamples.validSession;
    }

    @Test
    public void initializesWithoutFields() {
        Session session = new Session();
        assertNull(session.id);
        assertNull(session.userId);
        assertNull(session.beginTime);
        assertNull(session.endTime);
        assertNull(session.token);
    }

    @Test
    public void printsFields() {
        assertEquals("Session[id=null" +
                " userId=1" +
                " beginTime=2022-01-01T10:00" +
                " endTime=2022-01-01T12:00" +
                " token=sample-token" +
                "]", validSession.toString());
    }

    @Test
    public void clonesWithFields() {
        assertNull(validSession.withId(null).id);
        assertNull(validSession.withUserId(null).userId);
        assertNull(validSession.withBeginTime(null).beginTime);
        assertNull(validSession.withEndTime(null).endTime);
        assertNull(validSession.withToken(null).token);
    }

    @Test
    public void checksEquality() {
        assertEquals(SessionSamples.validSession, validSession.copy());
        assertNotEquals(SessionSamples.validSession, validSession.withId("0"));
        assertNotEquals(SessionSamples.validSession, validSession.withUserId(null));
        assertNotEquals(SessionSamples.validSession, validSession.withBeginTime(null));
        assertNotEquals(SessionSamples.validSession, validSession.withEndTime(null));
        assertNotEquals(SessionSamples.validSession, validSession.withToken(null));
    }
}
