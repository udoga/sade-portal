class VacationPageComponent extends PageComponent {
    vacation = this.record;
    employee = {};
    employeeApi = null;

    constructor(router, api, notifier, employeeApi) {
        super(router, api, notifier, "vacation");
        this.employeeApi = employeeApi;
    }

    loadRelations = function () {
        this.selectTab("employee");
    }

    loadTab = function (tab) {
        if (tab == "employee") this.loadEmployee();
    }

    loadEmployee = function () {
        this.employeeApi.read(this.vacation.employeeId)
            .then(json => json.data ? Object.assign(this.employee, json.data) : this.notifyError(json));
    }
}
