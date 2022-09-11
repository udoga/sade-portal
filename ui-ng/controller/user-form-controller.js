function UserFormController($scope, $window, $routeParams, api) {
  $scope.user = {};

  $scope.load = function() {
    if ($scope.editing())
      api.readUser($routeParams.id).then(function(response) { $scope.user = response.data.data; });
  };

  $scope.save = function() {
    if ($scope.editing()) $scope.update();
    else $scope.create();
  }

  $scope.create = function() {
    api.createUser($scope.user).then(function(response) { $window.location.href = '#!users'; });
  };

  $scope.update = function() {
    api.updateUser($routeParams.id, $scope.user).then(function(response) { $window.location.href = '#!users'; });
  };

  $scope.editing = function() {
    return $routeParams.id != undefined;
  }

  $scope.load();
}
