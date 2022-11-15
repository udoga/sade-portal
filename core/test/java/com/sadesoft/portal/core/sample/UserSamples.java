package com.sadesoft.portal.core.sample;

import com.sadesoft.portal.core.model.User;

public class UserSamples {
    public static User validUser = new User(
            null,
            "sample-username",
            "sample-password");

    public static User firstUser = new User(
            null,
            "first-username",
            "first-password");

    public static User secondUser = new User(
            null,
            "second-username",
            "second-password");
}
