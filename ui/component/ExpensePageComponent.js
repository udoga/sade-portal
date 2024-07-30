class ExpensePageComponent extends PageComponent {
    expense = this.record;
    receipts = [];
    employee = {};
    receiptApi = null;
    employeeApi = null;

    constructor(router, api, notifier, receiptApi, employeeApi) {
        super(router, api, notifier, "expense");
        this.receiptApi = receiptApi;
        this.employeeApi = employeeApi;
    }

    loadRelations = function() {
        this.selectTab("receipts");
    }

    loadTab = function (tab) {
        if (tab == "receipts") this.loadReceipts();
        else if (tab == "employee") this.loadEmployee();
    }

    loadReceipts = function () {
        this.receiptApi.listByExpenseId(this.router.getId())
            .then(json => json.data ? Object.assign(this.receipts, json.data) : this.notifyError(json));
    }

    loadEmployee = function () {
        this.employeeApi.read(this.expense.employeeId)
            .then(json => json.data ? Object.assign(this.employee, json.data) : this.notifyError(json));
    }

    removeReceipt = function (id) {
        if (confirm("Are you sure?"))
            this.receiptApi.delete(id)
                .then(json => json.data ? this.removeFromTable(this.receipts, json) : this.notifyError(json));
    }
}
