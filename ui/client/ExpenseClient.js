class ExpenseClient extends RestClient {
  constructor(router) {
    super(router, "/expenses/");
  }
}
