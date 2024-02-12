class LoginController {
  router = null;
  sessionClient = null;
  user = {};
  errorMessage = "";

  constructor(router, sessionClient) {
    this.router = router;
    this.sessionClient = sessionClient;
  }

  login = function() {
    this.sessionClient.login(this.user).then(json => json.data? this.accept(json.data) : this.reject(json.error));
  }

  accept = function(session) {
    localStorage.setItem("token", session.token);
    this.router.route("#/home");
  }

  reject = function(error) {
    this.errorMessage = error.message;
  }
}
