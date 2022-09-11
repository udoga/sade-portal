function LeaveFormController($scope, $window, $routeParams, api) {
  $scope.leave = {};

  $scope.load = function() {
    if ($scope.editing())
      api.readLeave($routeParams.id).then(function(response) { $scope.leave = response.data.data; });
  };

  $scope.save = function() {
    if ($scope.editing()) $scope.update();
    else $scope.create();
  }

  $scope.create = function() {
    api.createLeave($scope.leave).then(function(response) { $window.location.href = '#!leaves'; });
  };

  $scope.update = function() {
    api.updateLeave($routeParams.id, $scope.leave).then(function(response) {
        $window.location.href = '#!leaves';
    });
  };

  $scope.editing = function() {
    return $routeParams.id != undefined;
  }

  $scope.load();
}
