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
        this.loadOptions(employeeApi, this.employees);
        this.loadOptions(categoryApi, this.categories);
    }

    getFullName = function (employee) {
        return employee.firstName + " " + employee.lastName;
    }
}
