package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

public class User implements Record, Cloneable {
    public Integer id = null;
    public String username = null;
    public String fullName = null;

    public User() {
    }

    public User(String username, String fullName) {
        this(null, username, fullName);
    }

    public User(Integer id, String username, String fullName) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "User[id=" + id + " username=" + username + " fullName=" + fullName + "]";
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public User withId(Integer id) {
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

    private User copy() {
        try {
            return (User) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
