class ExpenseFormComponent extends FormComponent {
  expense = this.record;

  constructor(router, client, notifier) {
    super(router, client, "expense", notifier);
  }
}
