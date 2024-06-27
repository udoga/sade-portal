class EmployeePageComponent extends PageComponent {
    employee = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "employee");
    }
}
