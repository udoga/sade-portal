class VacationListComponent extends ListComponent {
    vacations = this.records;

    constructor(api, notifier) {
        super(api, notifier, "vacations");
    }

    load = function () {
        this.api.listDetailed().then(json => this.handleLoadResponse(json));
    }
}
