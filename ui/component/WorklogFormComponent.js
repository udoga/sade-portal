class WorklogFormComponent extends FormComponent {
    worklog = this.record;

    constructor(router, client, notifier) {
        super(router, client, notifier, "worklog");
    }
}
