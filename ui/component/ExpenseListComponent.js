class ExpenseListComponent extends ListComponent {
    expenses = this.records;
    employees = [];

    constructor(api, notifier) {
        super(api, notifier, "expenses");
    }

    loadAll = function (employeeApi) {
        this.load();
        employeeApi.list()
            .then(json => json.data ? Object.assign(this.employees, json.data) : this.notifyError(json));
    }

    getEmployeeName = function (employeeId) {
        for (let e of this.employees) if (e.id === employeeId) return (e.firstName + " " + e.lastName);
        return "-";
    }
}
