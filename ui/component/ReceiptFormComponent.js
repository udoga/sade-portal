class ReceiptFormComponent extends FormComponent {
    receipt = this.record;
    categories = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "receipt");
    }

    loadAll = function (categoryApi) {
        this.load();
        categoryApi.list().then(json => json.data? Object.assign(this.categories, json.data) : this.notifyError(json));
    }

    routeOnSuccess = function () {
        this.router.route("#/expense-page/" + this.receipt.expenseId);
    }
}
