class EmployeeListComponent extends ListComponent {
    employees = this.records;

    constructor(api, notifier) {
        super(api, notifier, "employees");
    }

    load = function () {
        this.api.listDetailed().then(json => this.handleLoadResponse(json));
    }
}
