class AssignmentFormComponent extends FormComponent {
    assignment = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "assignment");
    }
}
