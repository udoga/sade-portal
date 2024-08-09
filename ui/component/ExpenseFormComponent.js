class ExpenseFormComponent extends FormComponent {
    expense = this.record;
    employees = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "expense");
    }

    loadAll = function (employeeApi) {
        this.load();
        employeeApi.listSummarized()
            .then(json => json.data? Object.assign(this.employees, json.data) : this.notifyError(json));
    }

    getFullName = function (employee) {
        return employee.firstName + " " + employee.lastName;
    }
}
