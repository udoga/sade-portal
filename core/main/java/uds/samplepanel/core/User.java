package uds.samplepanel.core;

import uds.samplepanel.base.Record;

public class User extends Record {
    public final String username;
    public final String fullName;

    public User() {
        this(null, null);
    }

    public User(String username, String fullName) {
        this.id = null;
        this.username = username;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "User[id=" + id + " username=" + username + " fullName=" + fullName + "]";
    }
}
