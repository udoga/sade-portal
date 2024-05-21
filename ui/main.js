var notifier = new Notifier();
var httpClient = new HttpClient();
var router = new Router(notifier, httpClient);
var userClient = new UserClient(router);
var leaveClient = new LeaveClient(router);
var expenseClient = new ExpenseClient(router);
var receiptClient = new ReceiptClient(router);
var sessionClient = new SessionClient(router);
