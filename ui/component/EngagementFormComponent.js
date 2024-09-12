class EngagementFormComponent extends FormComponent {
    engagement = this.record;
    projects = [];
    employees = [];
    positions = [];

    constructor(router, api, notifier) {
        super(router, api, notifier);
    }

    loadAll = function (projectApi, employeeApi, positionApi) {
        this.load();
        this.loadOptions(projectApi, this.projects);
        this.loadOptions(employeeApi, this.employees);
        this.loadOptions(positionApi, this.positions);
    }
}
