class UserListComponent extends ListComponent {
    users = this.records;

    constructor(api, notifier) {
        super(api, notifier, "users");
    }
}
