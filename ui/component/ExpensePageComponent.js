class ExpensePageComponent extends PageComponent {
    expense = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "expense");
    }
}
