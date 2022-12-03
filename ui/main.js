var httpClient = new HttpClient();
var userClient = new UserClient(httpClient);
var receiptClient = new ReceiptClient(httpClient);
var personClient = new PersonClient(httpClient);
var leaveClient = new LeaveClient(httpClient);
var expenseClient = new ExpenseClient(httpClient);
var notifier = new Notifier();
var router = new Router(notifier);
