class EmployeePageComponent extends PageComponent {
    employee = this.record;

    constructor(router, client, notifier) {
        super(router, client, notifier, "employee");
    }
}
