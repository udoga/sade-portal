class VacationListComponent extends ListComponent {
    vacations = this.records;
    formatter = null;

    constructor(router, api, notifier, formatter) {
        super(router, api, notifier);
        this.formatter = formatter;
    }
}
