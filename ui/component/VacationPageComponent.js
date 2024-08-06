class VacationPageComponent extends PageComponent {
    vacation = this.record;
    formatter = null;

    constructor(router, api, notifier, formatter) {
        super(router, api, notifier, "vacation");
        this.formatter = formatter;
    }

    afterLoad = function () {
        this.selectTab("employee");
    }

    getEmployee = function () {
        return this.vacation.employee ? this.vacation.employee : {};
    }
}
