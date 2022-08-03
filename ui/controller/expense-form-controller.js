function ExpenseFormController($scope, $window, $routeParams, api) {
  $scope.expense = {};

  $scope.load = function() {
    if ($scope.editing())
      api.readExpense($routeParams.id).then(function(response) { $scope.expense = response.data.data; });
  };

  $scope.save = function() {
    if ($scope.editing()) $scope.update();
    else $scope.create();
  }

  $scope.create = function() {
    api.createExpense($scope.expense).then(function(response) { $window.location.href = '#!expenses'; });
  };

  $scope.update = function() {
    api.updateExpense($routeParams.id, $scope.expense).then(function(response) {
        $window.location.href = '#!expenses';
    });
  };

  $scope.editing = function() {
    return $routeParams.id != undefined;
  }

  $scope.load();
}
