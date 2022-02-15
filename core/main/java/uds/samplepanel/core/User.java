package uds.samplepanel.core;

import uds.samplepanel.base.Record;

public class User extends Record {
    public final String username;

    public User() {
        this(null);
    }

    public User(String username) {
        super(null);
        this.username = username;
    }

    @Override
    public String toString() {
        return "User[id=" + id + " username=" + username + "]";
    }
}
