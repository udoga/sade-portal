function Config($routeProvider) {
  this.bind = function(url, view) {
    $routeProvider.when(url, { templateUrl : "view/" + view + ".html"});
  };

  this.bind("/", "home");
  this.bind("/users", "user-list");
  this.bind("/users/new", "user-form");
  this.bind("/users/:id", "user-page");
  this.bind("/users/:id/edit", "user-form");
}
