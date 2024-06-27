class EmployeeFormComponent extends FormComponent {
    employee = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "employee");
    }
}
