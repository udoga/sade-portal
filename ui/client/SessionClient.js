class SessionClient extends RestClient {
  constructor(httpClient) {
    super(httpClient, "/sessions/");
  }

  login = function(user) {
    return this.create(user);
  }
}
