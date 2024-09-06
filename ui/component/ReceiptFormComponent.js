class ReceiptFormComponent extends FormComponent {
    receipt = this.record;
    categories = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "receipt");
    }

    loadAll = function (categoryApi) {
        this.load();
        this.loadOptions(categoryApi, this.categories);
    }

    routeOnSuccess = function () {
        this.router.route("#/expense-page/" + this.receipt.expenseId);
    }
}
