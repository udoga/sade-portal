class SessionPageComponent extends PageComponent {
    session = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "session");
    }
}
