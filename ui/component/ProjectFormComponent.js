class ProjectFormComponent extends FormComponent {
    project = this.record;

    constructor(router, client, notifier) {
        super(router, client, notifier, "project");
    }
}
