class ExpenseListComponent extends ListComponent {
    expenses = this.records;
    employees = [];

    constructor(client, notifier) {
        super(client, notifier, "expenses");
    }

    loadAll = function (employeeClient) {
        this.load();
        employeeClient.list()
            .then(json => json.data ? Object.assign(this.employees, json.data) : this.notifyError(json));
    }

    getEmployeeName = function (employeeId) {
        for (let e of this.employees) if (e.id === employeeId) return (e.firstName + " " + e.lastName);
        return "-";
    }
}
