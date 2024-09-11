class VacationPageComponent extends PageComponent {
    vacation = this.record;
    formatter = null;

    constructor(router, api, notifier, formatter) {
        super(router, api, notifier, "vacation", "detailed");
        this.formatter = formatter;
    }

    afterLoad = function () {
        this.selectTab("employee");
    }

    getEmployee = function () {
        return this.vacation.employee ? this.vacation.employee : {};
    }

    getCategory = function () {
        return this.vacation.category ? this.vacation.category : {};
    }
}
