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
        this.loadEmployees(employeeApi);
        this.loadCategories(categoryApi);
    }

    loadEmployees = function (employeeApi) {
        employeeApi.listSummarized()
            .then(json => json.data? Object.assign(this.employees, json.data) : this.notifyError(json));
    }

    loadCategories = function (categoryApi) {
        categoryApi.listSummarized()
            .then(json => json.data? Object.assign(this.categories, json.data) : this.notifyError(json));
    }

    getFullName = function (employee) {
        return employee.firstName + " " + employee.lastName;
    }
}
