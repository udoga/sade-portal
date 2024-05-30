class ExpensePageComponent extends PageComponent {
  expense = this.record;

  constructor(router, client) {
    super(router, client, "expense");
  }
}
