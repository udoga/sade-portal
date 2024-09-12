class LoginComponent {
    router = null;
    sessionApi = null;
    user = {};
    errorMessage = "";

    constructor(router, sessionApi) {
        this.router = router;
        this.sessionApi = sessionApi;
    }

    login = function () {
        this.sessionApi.login(this.user).then(json => json.data ? this.accept(json.data) : this.reject(json.error));
    }

    accept = function (session) {
        window.dispatchEvent(new CustomEvent("login-event", { detail: session }));
    }

    reject = function (error) {
        this.errorMessage = error.message;
    }
}
