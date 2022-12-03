class UserClient extends RestClient {
  constructor(httpClient) {
    super(httpClient, "/users/");
  }
}
