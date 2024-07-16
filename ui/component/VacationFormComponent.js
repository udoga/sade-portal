class VacationFormComponent extends FormComponent {
    vacation = this.record;
    categories = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "vacation");
    }

    saveVacation = function () {
        this.vacation.status = null;
        this.save();
    }

    loadAll = function (categoryApi) {
        this.load();
        categoryApi.list()
            .then(json => json.data ? Object.assign(this.categories, json.data) : this.notifyError(json));
    }
}
