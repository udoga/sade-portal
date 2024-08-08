class RoleListComponent extends ListComponent {
    roles = this.records;

    constructor(router, api, notifier) {
        super(router, api, notifier);
    }
}
