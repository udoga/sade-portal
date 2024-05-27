var notifier = new Notifier();
var httpClient = new HttpClient();
var router = new Router(notifier, httpClient);
var userClient = new UserClient();
var leaveClient = new LeaveClient();
var expenseClient = new ExpenseClient();
var receiptClient = new ReceiptClient();
var sessionClient = new SessionClient();
