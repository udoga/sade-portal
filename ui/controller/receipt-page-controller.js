function ReceiptPageController($scope, $routeParams, api) {
  $scope.receipt = {};

  $scope.load = function() {
    api.readReceipt($routeParams.id).then(function(response) { $scope.receipt = response.data.data; });
  };

  $scope.load();
}
