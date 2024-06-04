class EmployeeListComponent extends ListComponent {
  employees = this.records;

  constructor(client, notifier) {
    super(client, notifier, "employees");
  }
}
