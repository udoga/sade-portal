class ExpenseListController extends ListController {
  expenses = [];

  constructor(api) {
    super(api, "expenses");
  }
}