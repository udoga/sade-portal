var app = angular.module('app', ["ngRoute"]);

app.config(Config);
app.service('api', Api);
app.controller('ExpenseFormController', ExpenseFormController);
app.controller('ExpenseListController', ExpenseListController);
app.controller('ExpensePageController', ExpensePageController);
app.controller('LayoutController', LayoutController);
app.controller('PersonFormController', PersonFormController);
app.controller('PersonListController', PersonListController);
app.controller('PersonPageController', PersonPageController);
app.controller('ReceiptFormController', ReceiptFormController);
app.controller('ReceiptListController', ReceiptListController);
app.controller('ReceiptPageController', ReceiptPageController);
app.controller('UserFormController', UserFormController);
app.controller('UserListController', UserListController);
app.controller('UserPageController', UserPageController);
