class ExpensePageComponent extends PageComponent {
    expense = this.record;
    receipts = [];
    selectedTitle = "";
    receiptApi = null;

    constructor(router, api, notifier, receiptApi) {
        super(router, api, notifier, "expense");
        this.receiptApi = receiptApi;
    }

    loadRelations = function() {
        this.select("receipts");
    }

    select = function (title) {
        this.selectedTitle = title;
        if (title == "receipts") this.loadReceipts();
    }

    isSelected = function (title) {
        return this.selectedTitle === title;
    }

    getClass = function (title) {
        return (this.selectedTitle === title)? "selected" : "";
    }

    loadReceipts = function () {
        this.receiptApi.listByExpenseId(this.router.getId())
            .then(json => json.data ? Object.assign(this.receipts, json.data) : this.notifyError(json));
    }
}
