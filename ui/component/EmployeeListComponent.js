class EmployeeListComponent extends ListComponent {
    employees = this.records;

    constructor(api, notifier) {
        super(api, notifier, "employees");
    }

    load = function () {
        this.api.listWithUser().then(json => this.handleLoadResponse(json));
    }
}
