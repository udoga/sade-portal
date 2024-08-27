class EngagementFormComponent extends FormComponent {
    engagement = this.record;
    projects = [];
    employees = [];
    positions = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "engagement");
    }

    loadAll = function (projectApi, employeeApi, positionApi) {
        this.load();
        this.loadSummarized(projectApi, this.projects);
        this.loadSummarized(employeeApi, this.employees);
        this.loadSummarized(positionApi, this.positions);
    }

    routeOnSuccess = function () {
        this.router.route("#/project-page/" + this.engagement.projectId);
    }
}
