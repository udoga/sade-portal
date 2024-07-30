class EmployeePageComponent extends PageComponent {
    employee = this.record;
    user = {};
    userApi = null;

    constructor(router, api, notifier, userApi) {
        super(router, api, notifier, "employee");
        this.userApi = userApi;
    }

    loadRelations = function () {
        this.selectTab("user");
    }

    loadTab = function (tab) {
        if (tab == "user") this.loadUser();
    }

    loadUser = function () {
        this.userApi.read(this.employee.userId)
            .then(json => json.data ? Object.assign(this.user, json.data) : this.notifyError(json));
    }
}
