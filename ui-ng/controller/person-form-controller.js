function PersonFormController($scope, $window, $routeParams, api) {
  $scope.person = {};

  $scope.load = function() {
    if ($scope.editing())
      api.readPerson($routeParams.id).then(function(response) { $scope.person = response.data.data; });
  };

  $scope.save = function() {
    if ($scope.editing()) $scope.update();
    else $scope.create();
  }

  $scope.create = function() {
    api.createPerson($scope.person).then(function(response) { $window.location.href = '#!people'; });
  };

  $scope.update = function() {
    api.updatePerson($routeParams.id, $scope.person).then(function(response) { $window.location.href = '#!people'; });
  };

  $scope.editing = function() {
    return $routeParams.id != undefined;
  }

  $scope.load();
}
