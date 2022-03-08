var app = angular.module('app', []);

class User {
  constructor(id, username) {
    this.id = id;
    this.username = username;
  }
}

app.controller('UserController', function($scope) {
  $scope.username = "";
  $scope.users = [];
  $scope.users.push(new User(0, "sample"));
});
