class ExpensePageComponent extends PageComponent {
    expense = this.record;

    constructor(router, client, notifier) {
        super(router, client, notifier, "expense");
    }
}
