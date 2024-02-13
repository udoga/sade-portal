class SessionClient extends RestClient {
  constructor(httpClient) {
    super(httpClient, "/sessions/");
  }

  login = function(user) {
    return this.create(user);
  }

  logout = function() {
    return this.httpClient.post(this.url + "logout", {
      headers: this.getHeaders()
    }).then(response => response.json());
  }
}
