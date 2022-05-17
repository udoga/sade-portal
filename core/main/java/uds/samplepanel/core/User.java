package uds.samplepanel.core;

import uds.samplepanel.base.Record;

public class User implements Record {
    public Integer id;
    public String username;
    public String fullName;

    public User() {
        this(null, null, null);
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
}
