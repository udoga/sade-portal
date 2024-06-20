package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.util.Objects;

public class Client implements Record {
    public String id;
    public String name;

    public Client(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Client(){}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Client)) return false;
        Client c = (Client) o;
        return Objects.equals(id, c.id) && Objects.equals(name, c.name);
    }

    @Override
    public String toString() {
        return "Client[" +
                "id=" + id +
                " name=" + name +
                ']';
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Client copy() {
        try {
            return (Client) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Client withId(String id) {
        Client client = copy();
        client.id = id;
        return client;
    }

    public Client withName(String name) {
        Client client = copy();
        client.name = name;
        return client;
    }
}
