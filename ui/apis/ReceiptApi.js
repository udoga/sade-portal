class ReceiptApi extends RestApi {
    constructor() {
        super("/receipts/");
    }

    listByExpenseId = function (expenseId) {
        return this.list("?expenseId=" + expenseId);
    }
}