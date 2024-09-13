class ExpenseListComponent extends ListComponent {
    expenses = this.records;
    employees = [];
    employeeApi = null;

    constructor(router, api, notifier, employeeApi) {
        super(router, api, notifier, "detailed");
        this.employeeApi = employeeApi;
    }

    afterLoad = function () {
        this.loadOptions(this.employeeApi, this.employees);
    }
}
