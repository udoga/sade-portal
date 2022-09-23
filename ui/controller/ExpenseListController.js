class ExpenseListController extends ListController {
  expenses = [];

  constructor(api, notifier) {
    super(api, "expenses", notifier);
  }
}
