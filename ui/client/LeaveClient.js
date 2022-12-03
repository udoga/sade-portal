class LeaveClient extends RestClient {
  constructor(httpClient) {
    super(httpClient, "/leaves/");
  }
}
