package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.util.Objects;

public class Sector implements Record {
    public String id;
    public String name;

    public Sector(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Sector(){}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Sector)) return false;
        Sector w = (Sector) o;
        return Objects.equals(id, w.id) && Objects.equals(name, w.name);
    }

    @Override
    public String toString() {
        return "Sector[" +
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

    public Sector copy() {
        try {
            return (Sector) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Sector withId(String id) {
        Sector sector = copy();
        sector.id = id;
        return sector;
    }

    public Sector withName(String name) {
        Sector sector = copy();
        sector.name = name;
        return sector;
    }
}
