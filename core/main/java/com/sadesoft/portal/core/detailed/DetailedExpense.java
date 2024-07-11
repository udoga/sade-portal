package com.sadesoft.portal.core.detailed;

import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Expense;

public class DetailedExpense extends Expense {
    public Employee employee;

    public DetailedExpense(Expense e) {
        super(e);
    }

    public DetailedExpense(Expense e, Employee m) {
        super(e);
        this.employee = m;
    }

    public DetailedExpense copy() {
        try {
            return (DetailedExpense) clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public DetailedExpense withEmployee(Employee employee) {
        DetailedExpense detailedExpense = copy();
        detailedExpense.employee = employee;
        return detailedExpense;
    }
}
