class ExpenseListController extends ListController {
  expenses = [];

  constructor(client, notifier) {
    super(client, "expenses", notifier);
  }
}
