var httpApi = new HttpApi();
var userApi = new UserApi(httpApi);
var receiptApi = new ReceiptApi(httpApi);
var personApi = new PersonApi(httpApi);
var router = new Router();
