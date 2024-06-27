class ProjectFormComponent extends FormComponent {
    project = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "project");
    }
}
