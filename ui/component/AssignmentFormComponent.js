class AssignmentFormComponent extends FormComponent {
    assignment = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "assignment");
    }

    quit = function () {
        this.router.route("#/user-page/" + this.assignment.userId);
    }
}
