function ItemPageController($scope, $routeParams, api) {
  $scope.item = {};

  $scope.load = function() {
    api.readItem($routeParams.id).then(function(response) { $scope.item = response.data.data; });
  };

  $scope.load();
}
