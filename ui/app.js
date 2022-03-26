var app = angular.module('app', ["ngRoute"]);

app.config(Config);
app.service('api', Api);
app.controller('LayoutController', LayoutController);
app.controller('UserFormController', UserFormController);
app.controller('UserListController', UserListController);
app.controller('UserPageController', UserPageController);
app.controller('ItemFormController', ItemFormController);
app.controller('ItemListController', ItemListController);
app.controller('ItemPageController', ItemPageController);
