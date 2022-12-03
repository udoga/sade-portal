class ReceiptClient extends RestClient {
  constructor(httpClient) {
    super(httpClient, "/receipts/");
  }
}
