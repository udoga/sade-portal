class WorklogFormComponent extends FormComponent {
    worklog = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "worklog");
    }
}
