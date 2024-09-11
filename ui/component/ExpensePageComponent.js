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
        this.selectTab("receipts");
    }

    loadReceipts = function () {
        this.loadRelations(this.receiptApi, this.receipts);
    }

    removeReceipt = function (id) {
        this.removeRelation(this.receiptApi, this.receipts, id);
    }

    getEmployee = function () {
        return this.expense.employee ? this.expense.employee : {};
    }
}
