class ExpenseListComponent extends ListComponent {
    expenses = this.records;

    constructor(api, notifier) {
        super(api, notifier, "expenses");
    }

    load = function () {
        this.api.listDetailed().then(json => this.handleLoadResponse(json));
    }
}
