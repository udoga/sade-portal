class VacationPageComponent extends PageComponent {
    vacation = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "vacation");
    }

    afterLoad = function () {
        this.selectTab("employee");
    }

    getEmployee = function () {
        return this.vacation.employee ? this.vacation.employee : {};
    }
}
