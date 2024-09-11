class ExpenseListComponent extends ListComponent {
    expenses = this.records;
    employees = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "detailed");
    }

    loadAll = function (employeeApi) {
        this.load();
        this.loadOptions(employeeApi, this.employees);
    }
}
