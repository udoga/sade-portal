class VacationListComponent extends ListComponent {
    vacations = this.records;
    formatter = null;

    constructor(api, notifier, formatter) {
        super(api, notifier, "vacations");
        this.formatter = formatter;
    }

    load = function () {
        this.api.listDetailed().then(json => this.handleLoadResponse(json));
    }
}
