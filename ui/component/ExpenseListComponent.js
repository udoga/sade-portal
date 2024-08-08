class ExpenseListComponent extends ListComponent {
    expenses = this.records;

    constructor(router, api, notifier) {
        super(router, api, notifier, "expenses");
    }

    load = function () {
        this.api.listDetailed().then(json => this.handleLoadResponse(json));
    }
}
