function ReceiptListController($scope, $window, api) {
  $scope.receipts = [];

  $scope.load = function() {
    api.listReceipts().then(function(response) { $scope.receipts = response.data.data; });
  }

  $scope.delete = function(id) {
    api.deleteReceipt(id).then(function(response) { $scope.load(); });
  }

  $scope.load();
};
