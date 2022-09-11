function PersonPageController($scope, $routeParams, api) {
  $scope.person = {};

  $scope.load = function() {
    api.readPerson($routeParams.id).then(function(response) { $scope.person = response.data.data; });
  };

  $scope.load();
}
