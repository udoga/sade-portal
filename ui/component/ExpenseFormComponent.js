class ExpenseFormComponent extends FormComponent {
    expense = this.record;
    employees = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "expense");
    }

    loadAll = function (employeeApi) {
        this.load();
        this.loadSummarized(employeeApi, this.employees);
    }

    getFullName = function (employee) {
        return employee.firstName + " " + employee.lastName;
    }
}
