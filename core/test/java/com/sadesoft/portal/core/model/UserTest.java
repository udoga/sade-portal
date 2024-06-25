package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.util.StringUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    public void findsEncryptedPassword() {
        User user = new User(null, "sample-username", "sample-password");
        assertEquals(StringUtil.hashSha512(user.password), user.findEncryptedPassword(""));
        assertEquals(StringUtil.hashSha512("salt" + user.password), user.findEncryptedPassword("salt"));
        assertEquals(user.withPassword(user.findEncryptedPassword("")), user.withEncryptedPassword(""));
    }
}
