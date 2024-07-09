class ReceiptFormComponent extends FormComponent {
    receipt = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "receipt");
    }

    quit = function () {
        this.router.route("#/expense-page/" + this.receipt.expenseId);
    }
}
