class UserListComponent extends ListComponent {
    users = this.records;

    constructor(router, api, notifier) {
        super(router, api, notifier, "users");
    }
}
