class UserClient extends RestClient {
  constructor(router) {
    super(router, "/users/");
  }
}
