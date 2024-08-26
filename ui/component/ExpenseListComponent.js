class ExpenseListComponent extends ListComponent {
    expenses = this.records;
    employees = [];

    constructor(router, api, notifier) {
        super(router, api, notifier);
    }

    loadAll = function (employeeApi) {
        this.load("detailed");
        this.loadSummarized(employeeApi, this.employees);
    }
}
