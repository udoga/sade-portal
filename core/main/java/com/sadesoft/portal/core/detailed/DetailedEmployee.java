package com.sadesoft.portal.core.detailed;

import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.User;

public class DetailedEmployee extends Employee {
    public User user;

    public DetailedEmployee(Employee e) {
        super(e);
    }

    public DetailedEmployee(Employee e, User u) {
        super(e);
        this.user = u;
    }

    public DetailedEmployee copy() {
        try {
            return (DetailedEmployee) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public DetailedEmployee withUser(User user) {
        DetailedEmployee detailedEmployee = copy();
        detailedEmployee.user = user;
        return detailedEmployee;
    }

    public DetailedEmployee withFirstName(String firstName) {
        DetailedEmployee detailedEmployee = copy();
        detailedEmployee.firstName = firstName;
        return detailedEmployee;
    }

    public DetailedEmployee withLastName(String lastName) {
        DetailedEmployee detailedEmployee = copy();
        detailedEmployee.lastName = lastName;
        return detailedEmployee;
    }
}
