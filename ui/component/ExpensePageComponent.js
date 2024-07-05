class ExpensePageComponent extends PageComponent {
    expense = this.record;
    receipts = [];
    receiptApi = null;

    constructor(router, api, notifier, receiptApi) {
        super(router, api, notifier, "expense");
        this.receiptApi = receiptApi;
    }

    loadRelations = function() {
        this.selectTab("receipts");
    }

    loadTab = function (tab) {
        if (tab == "receipts") this.loadReceipts();
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
}
