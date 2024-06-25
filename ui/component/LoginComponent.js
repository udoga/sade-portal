class LoginComponent {
    router = null;
    sessionClient = null;
    user = {};
    errorMessage = "";

    constructor(router, sessionClient) {
        this.router = router;
        this.sessionClient = sessionClient;
    }

    login = function () {
        this.sessionClient.login(this.user).then(json => json.data ? this.accept(json.data) : this.reject(json.error));
    }

    accept = function (session) {
        window.dispatchEvent(new CustomEvent("login-event", { detail: session.token }));
    }

    reject = function (error) {
        this.errorMessage = error.message;
    }
}
