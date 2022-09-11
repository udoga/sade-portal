function LeavePageController($scope, $routeParams, api) {
  $scope.leave = {};

  $scope.load = function() {
    api.readLeave($routeParams.id).then(function(response) { $scope.leave = response.data.data; });
  };

  $scope.load();
}
