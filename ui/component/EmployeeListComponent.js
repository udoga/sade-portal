class EmployeeListComponent extends ListComponent {
    employees = this.records;

    constructor(router, api, notifier) {
        super(router, api, notifier, "detailed");
    }
}
