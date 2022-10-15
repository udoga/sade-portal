class ExpenseFormController extends FormController {
  expense = {};

  constructor(router, api, notifier) {
    super(router, api, "expense", notifier);
  }

  getRecord = function() {
    return this.expense;
  }
}
