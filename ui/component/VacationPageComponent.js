class VacationPageComponent extends PageComponent {
    vacation = this.record;

    constructor(router, client, notifier) {
        super(router, client, notifier, "vacation");
    }
}
