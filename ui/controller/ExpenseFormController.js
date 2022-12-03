class ExpenseFormController extends FormController {
  expense = {};

  constructor(router, client, notifier) {
    super(router, client, "expense", notifier);
  }

  getRecord = function() {
    return this.expense;
  }
}
