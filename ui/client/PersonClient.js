class PersonClient extends RestClient {
  constructor(httpClient) {
    super(httpClient, "/people/");
  }
}
