class ReceiptFormComponent extends FormComponent {
    receipt = this.record;
    categories = [];

    constructor(router, api, notifier) {
        super(router, api, notifier);
    }

    loadAll = function (categoryApi) {
        this.load();
        this.loadOptions(categoryApi, this.categories);
    }
}
