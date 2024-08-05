class ExpensePageComponent extends PageComponent {
    expense = this.record;
    receiptApi = null;
    receipts = [];

    constructor(router, api, notifier, receiptApi) {
        super(router, api, notifier, "expense");
        this.receiptApi = receiptApi;
    }

    afterLoad = function () {
        this.loadReceipts();
        this.selectTab("receipts");
    }

    loadReceipts = function () {
        this.receiptApi.listByExpenseId(this.router.getId())
            .then(json => json.data ? Object.assign(this.receipts, json.data) : this.notifyError(json));
    }

    removeReceipt = function (id) {
        if (confirm("Are you sure?"))
            this.receiptApi.delete(id)
                .then(json => json.data ? this.removeFromTable(this.receipts, json) : this.notifyError(json));
    }

    getEmployee = function () {
        return this.expense.employee ? this.expense.employee : {};
    }
}
