function Config($routeProvider) {
  this.bind = function(url, view) {
    $routeProvider.when(url, { templateUrl : "view/" + view + ".html"});
  };

  this.bind("/", "home");
  this.bind("/users", "user-list");
  this.bind("/users/new", "user-form");
  this.bind("/users/:id", "user-page");
  this.bind("/users/:id/edit", "user-form");
  this.bind("/items", "item-list");
  this.bind("/items/new", "item-form");
  this.bind("/items/:id", "item-page");
  this.bind("/items/:id/edit", "item-form");
}
