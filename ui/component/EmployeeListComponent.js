class EmployeeListComponent extends ListComponent {
    employees = this.records;

    constructor(router, api, notifier) {
        super(router, api, notifier, "employees");
    }

    load = function () {
        this.api.listDetailed().then(json => this.handleLoadResponse(json));
    }
}
