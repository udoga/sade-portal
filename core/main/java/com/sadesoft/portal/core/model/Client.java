package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.util.Objects;

public class Client implements Record {
    public String id;
    public String sectorId;
    public String name;

    public Client(){}

    public Client(String id, String sectorId, String name) {
        this.id = id;
        this.sectorId = sectorId;
        this.name = name;
    }

    public Client(Client c) {
        this.id = c.id;
        this.sectorId = c.sectorId;
        this.name = c.name;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Client)) return false;
        Client c = (Client) o;
        return Objects.equals(id, c.id) && Objects.equals(sectorId, c.sectorId) && Objects.equals(name, c.name);
    }

    @Override
    public String toString() {
        return "Client[" +
                "id=" + id +
                " sectorId=" + sectorId +
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

    public Client withSectorId(String sectorId) {
        Client client = copy();
        client.sectorId = sectorId;
        return client;
    }

    public Client withName(String name) {
        Client client = copy();
        client.name = name;
        return client;
    }
}
