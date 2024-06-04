class EmployeeFormComponent extends FormComponent {
  employee = this.record;

  constructor(router, client, notifier) {
    super(router, client, "employee", notifier);
  }
}
