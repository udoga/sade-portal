var app = angular.module('app', ["ngRoute"]);

app.config(Config);
app.service('api', Api);
app.controller('LayoutController', LayoutController);
app.controller('ItemFormController', ItemFormController);
app.controller('ItemListController', ItemListController);
app.controller('ItemPageController', ItemPageController);
app.controller('PersonFormController', PersonFormController);
app.controller('PersonListController', PersonListController);
app.controller('PersonPageController', PersonPageController);
app.controller('UserFormController', UserFormController);
app.controller('UserListController', UserListController);
app.controller('UserPageController', UserPageController);
