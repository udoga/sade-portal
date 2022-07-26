function ReceiptFormController($scope, $window, $routeParams, api) {
  $scope.receipt = {};

  $scope.load = function() {
    if ($scope.editing())
      api.readReceipt($routeParams.id).then(function(response) { $scope.receipt = response.data.data; });
  };

  $scope.save = function() {
    if ($scope.editing()) $scope.update();
    else $scope.create();
  }

  $scope.create = function() {
    api.createReceipt($scope.receipt).then(function(response) { $window.location.href = '#!receipts'; });
  };

  $scope.update = function() {
    api.updateReceipt($routeParams.id, $scope.receipt).then(function(response) {
      $window.location.href = '#!receipts';
    });
  };

  $scope.editing = function() {
    return $routeParams.id != undefined;
  }

  $scope.load();
}
