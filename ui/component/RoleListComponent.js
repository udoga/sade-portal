class RoleListComponent extends ListComponent {
    roles = this.records;

    constructor(api, notifier) {
        super(api, notifier, "roles");
    }
}
