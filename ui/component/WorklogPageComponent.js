class WorklogPageComponent extends PageComponent {
    worklog = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "worklog", "detailed");
    }

    afterLoad = function () {
        this.selectTab("project");
    }

    getProject = function () {
        return this.worklog.project ? this.worklog.project : {};
    }

    getEmployee = function () {
        return this.worklog.employee ? this.worklog.employee : {};
    }

    getActivity = function () {
        return this.worklog.activity ? this.worklog.activity : {};
    }
}
