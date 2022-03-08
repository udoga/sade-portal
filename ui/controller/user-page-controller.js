function UserPageController($scope, $routeParams, api) {
  $scope.user = {};

  $scope.load = function() {
    api.readUser($routeParams.id).then(function(response) { $scope.user = response.data.data; });
  };

  $scope.load();
}
