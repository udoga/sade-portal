package com.sadesoft.portal.core.detailed;

import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Vacation;
import com.sadesoft.portal.core.model.VacationCategory;

public class DetailedVacation extends Vacation {
    public Employee employee;
    public VacationCategory category;

    public DetailedVacation(Vacation v) {
        super(v);
    }

    public DetailedVacation(Vacation v, Employee e, VacationCategory c) {
        super(v);
        this.employee = e;
        this.category = c;
    }

    public DetailedVacation copy() {
        try {
            return (DetailedVacation) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public DetailedVacation withEmployee(Employee employee) {
        DetailedVacation detailedVacation = copy();
        detailedVacation.employee = employee;
        return detailedVacation;
    }

    public DetailedVacation withCategory(VacationCategory category) {
        DetailedVacation detailedVacation = copy();
        detailedVacation.category = category;
        return detailedVacation;
    }
}