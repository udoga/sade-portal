class ExpenseListComponent extends ListComponent {
    expenses = this.records;

    constructor(router, api, notifier) {
        super(router, api, notifier);
    }
}
