class ExpensePageComponent extends PageComponent {
    expense = this.record;
    receiptApi = null;
    receipts = [];

    constructor(router, api, notifier, receiptApi) {
        super(router, api, notifier, "expense", "detailed");
        this.receiptApi = receiptApi;
    }

    afterLoad = function () {
        this.loadReceipts();
    }

    loadReceipts = function (reload = false) {
        this.loadRelations("receipts", this.receiptApi, this.receipts, reload);
    }

    removeReceipt = function (id) {
        this.removeRelation(this.receiptApi, this.receipts, id);
    }

    getEmployee = function () {
        return this.expense.employee ? this.expense.employee : {};
    }
}
