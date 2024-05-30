class ExpenseListComponent extends ListComponent {
  expenses = this.records;

  constructor(client, notifier) {
    super(client, "expenses", notifier);
  }
}
