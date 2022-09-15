class ExpenseFormController extends FormController {
  expense = {};

  constructor(router, api) {
    super(router, api, "expense");
  }

  getRecord = function() {
    return this.expense;
  }
}
