package com.sadesoft.portal.core.model;

import java.util.Objects;
import com.sadesoft.portal.base.repo.Record;
import com.sadesoft.portal.base.util.StringUtil;

public class User implements Record {
    public String id;
    public String username;
    public String password;

    public User() {
    }

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) return false;
        User u = (User) o;
        return Objects.equals(id, u.id) &&
                Objects.equals(username, u.username) &&
                Objects.equals(password, u.password);
    }

    @Override
    public String toString() {
        return "User[" +
                "id=" + id +
                " username=" + username +
                " password=" + password +
                "]";
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public User copy() {
        try {
            return (User) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public User withId(String id) {
        User user = copy();
        user.id = id;
        return user;
    }

    public User withUsername(String username) {
        User user = copy();
        user.username = username;
        return user;
    }

    public User withPassword(String password) {
        User user = copy();
        user.password = password;
        return user;
    }

    public User withEncryptedPassword(String salt) {
        return withPassword(findEncryptedPassword(salt));
    }

    public String findEncryptedPassword(String salt) {
        return StringUtil.hashSha512(salt + password);
    }
}
