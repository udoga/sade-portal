function Config($routeProvider) {
  this.bind = function(url, view) {
    $routeProvider.when(url, { templateUrl: "view/" + view + ".html"});
  };

  $routeProvider.when("/", { redirectTo: '/home' });
  this.bind("/home", "home");
  this.bind("/people", "person-list");
  this.bind("/people/new", "person-form");
  this.bind("/people/:id", "person-page");
  this.bind("/people/:id/edit", "person-form");
  this.bind("/receipts", "receipt-list");
  this.bind("/receipts/new", "receipt-form");
  this.bind("/receipts/:id", "receipt-page");
  this.bind("/receipts/:id/edit", "receipt-form");
  this.bind("/users", "user-list");
  this.bind("/users/new", "user-form");
  this.bind("/users/:id", "user-page");
  this.bind("/users/:id/edit", "user-form");
}
