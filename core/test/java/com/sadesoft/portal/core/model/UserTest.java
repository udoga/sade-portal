package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.util.StringUtil;
import com.sadesoft.portal.core.sample.UserSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private User validUser;

    @BeforeEach
    public void setup() {
        validUser = UserSamples.validUser;
    }

    @Test
    public void initializesWithoutFields() {
        User user = new User();
        assertNull(user.id);
        assertNull(user.username);
        assertNull(user.password);
    }

    @Test
    public void printsFields() {
        assertEquals("User[id=null" +
                " username=sample-username" +
                " password=sample-password" +
                "]", validUser.toString());
    }

    @Test
    public void clonesWithFields() {
        assertNull(validUser.withId(null).id);
        assertNull(validUser.withUsername(null).username);
        assertNull(validUser.withPassword(null).password);
    }

    @Test
    public void checksEquality() {
        assertEquals(UserSamples.validUser, validUser.copy());
        assertNotEquals(UserSamples.validUser, validUser.withId("0"));
        assertNotEquals(UserSamples.validUser, validUser.withUsername(null));
        assertNotEquals(UserSamples.validUser, validUser.withPassword(null));
    }

    @Test
    public void findsEncryptedPassword() {
        assertEquals(StringUtil.hashSha256(validUser.password), validUser.findEncryptedPassword(""));
        assertEquals(StringUtil.hashSha256("salt" + validUser.password), validUser.findEncryptedPassword("salt"));
        assertEquals(validUser.withPassword(validUser.findEncryptedPassword("")), validUser.withEncryptedPassword(""));
    }
}
