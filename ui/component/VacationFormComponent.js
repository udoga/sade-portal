class VacationFormComponent extends FormComponent {
    vacation = this.record;
    employees = [];
    categories = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "vacation");
    }

    saveVacation = function () {
        this.vacation.status = null;
        this.save();
    }

    loadAll = function (employeeApi, categoryApi) {
        this.load();
        employeeApi.list().then(json => json.data? Object.assign(this.employees, json.data) : this.notifyError(json));
        categoryApi.list().then(json => json.data? Object.assign(this.categories, json.data) : this.notifyError(json));
    }

    getFullName = function (employee) {
        return employee.firstName + " " + employee.lastName;
    }
}
