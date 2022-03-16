function ItemFormController($scope, $window, $routeParams, api) {
  $scope.item = {};

  $scope.load = function() {
    if ($scope.editing())
      api.readItem($routeParams.id).then(function(response) { $scope.item = response.data.data; });
  };

  $scope.save = function() {
    if ($scope.editing()) $scope.update();
    else $scope.create();
  }

  $scope.create = function() {
    api.createItem($scope.item).then(function(response) { $window.location.href = '#!items'; });
  };

  $scope.update = function() {
    api.updateItem($routeParams.id, $scope.item).then(function(response) { $window.location.href = '#!items'; });
  };

  $scope.editing = function() {
    return $routeParams.id != undefined;
  }

  $scope.load();
}
