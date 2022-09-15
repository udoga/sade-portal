class ExpensePageController extends PageController {
  expense = {};

  constructor(router, api) {
    super(router, api, "expense");
  }

  getRecord = function() {
    return this.expense;
  }
}
