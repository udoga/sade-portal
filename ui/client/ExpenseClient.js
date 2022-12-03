class ExpenseClient extends RestClient {
  constructor(httpClient) {
    super(httpClient, "/expenses/");
  }
}
