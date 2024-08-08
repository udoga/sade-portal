class VacationListComponent extends ListComponent {
    vacations = this.records;
    formatter = null;

    constructor(router, api, notifier, formatter) {
        super(router, api, notifier, "vacations");
        this.formatter = formatter;
    }

    load = function () {
        this.api.listDetailed().then(json => this.handleLoadResponse(json));
    }
}
