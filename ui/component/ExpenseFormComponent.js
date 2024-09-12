class ExpenseFormComponent extends FormComponent {
    expense = this.record;
    employees = [];

    constructor(router, api, notifier) {
        super(router, api, notifier);
    }

    loadAll = function (employeeApi) {
        this.load();
        this.loadOptions(employeeApi, this.employees);
    }
}
