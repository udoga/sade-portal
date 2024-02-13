class ExpenseListComponent extends ListComponent {
  expenses = [];

  constructor(client, notifier) {
    super(client, "expenses", notifier);
  }
}
