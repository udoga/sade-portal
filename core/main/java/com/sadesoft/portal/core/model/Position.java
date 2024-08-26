package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.util.Objects;

public class Position implements Record {
    public String id;
    public String name;

    public Position(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Position(){}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) return false;
        Position p = (Position) o;
        return Objects.equals(id, p.id) && Objects.equals(name, p.name);
    }

    @Override
    public String toString() {
        return "Position[" +
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

    public Position copy() {
        try {
            return (Position) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Position withId(String id) {
        Position position = copy();
        position.id = id;
        return position;
    }

    public Position withName(String name) {
        Position position = copy();
        position.name = name;
        return position;
    }
}
