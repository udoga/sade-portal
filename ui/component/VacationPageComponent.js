class VacationPageComponent extends PageComponent {
    vacation = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "vacation");
    }
}
