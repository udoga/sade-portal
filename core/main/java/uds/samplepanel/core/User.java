package uds.samplepanel.core;

import uds.samplepanel.base.Record;

import java.time.LocalDate;

public class User extends Record {
    public final String username;
    public final LocalDate birthDate;

    public User() {
        this(null, null);
    }

    public User(String username, LocalDate birthDate) {
        this.id = null;
        this.username = username;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User[id=" + id + " username=" + username + " birthDate=" + birthDate + "]";
    }
}
