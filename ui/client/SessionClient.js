class SessionClient extends RestClient {
  constructor() {
    super("/sessions/");
  }

  login = function(user) {
    return this.create(user);
  }

  logout = function() {
    return this.post(this.url + "logout", { headers: this.getHeaders() }).then(response => response.json());
  }
}
