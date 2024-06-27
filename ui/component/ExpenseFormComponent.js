class ExpenseFormComponent extends FormComponent {
    expense = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "expense");
    }
}
