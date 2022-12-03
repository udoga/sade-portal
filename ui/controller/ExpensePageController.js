class ExpensePageController extends PageController {
  expense = {};

  constructor(router, client) {
    super(router, client, "expense");
  }

  getRecord = function() {
    return this.expense;
  }
}
