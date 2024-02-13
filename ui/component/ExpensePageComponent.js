class ExpensePageComponent extends PageComponent {
  expense = {};

  constructor(router, client) {
    super(router, client, "expense");
  }

  getRecord = function() {
    return this.expense;
  }
}
