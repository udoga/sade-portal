package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.repo.Record;

import java.util.Objects;

public class VacationCategory implements Record {
    public String id;
    public String name;

    public VacationCategory(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public VacationCategory(){}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof VacationCategory)) return false;
        VacationCategory w = (VacationCategory) o;
        return Objects.equals(id, w.id) && Objects.equals(name, w.name);
    }

    @Override
    public String toString() {
        return "VacationCategory[" +
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

    public VacationCategory copy() {
        try {
            return (VacationCategory) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public VacationCategory withId(String id) {
        VacationCategory vacationCategory = copy();
        vacationCategory.id = id;
        return vacationCategory;
    }

    public VacationCategory withName(String name) {
        VacationCategory vacationCategory = copy();
        vacationCategory.name = name;
        return vacationCategory;
    }
}
