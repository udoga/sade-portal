package com.sadesoft.portal.core.model;

import java.util.Objects;
import com.sadesoft.portal.base.repo.Record;

public class User implements Record, Cloneable {
    public String id = null;
    public String username = null;
    public String fullName = null;

    public User() {
    }

    public User(String id, String username, String fullName) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User u)) return false;
        return Objects.equals(id, u.id) &&
                Objects.equals(username, u.username) &&
                Objects.equals(fullName, u.fullName);
    }

    @Override
    public String toString() {
        return "User[" +
                "id=" + id +
                " username=" + username +
                " fullName=" + fullName +
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

    public User withFullName(String fullName) {
        User user = copy();
        user.fullName = fullName;
        return user;
    }
}
