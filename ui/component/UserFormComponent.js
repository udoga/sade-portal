class UserFormComponent extends FormComponent {
    user = this.record;
    retypedPassword = "";

    constructor(router, api, notifier) {
        super(router, api, notifier, "user");
    }

    check = function () {
        if (this.user.password === this.retypedPassword) this.save();
        else notifier.notify(new Notification("error", "Passwords don't match, please try again."));
    }
}
