var httpClient = new HttpClient();
var userClient = new UserClient(httpClient);
var leaveClient = new LeaveClient(httpClient);
var expenseClient = new ExpenseClient(httpClient);
var receiptClient = new ReceiptClient(httpClient);
var sessionClient = new SessionClient(httpClient);
var notifier = new Notifier();
var router = new Router(notifier, httpClient);
