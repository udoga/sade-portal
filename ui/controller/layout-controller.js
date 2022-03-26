function LayoutController($scope, $location, $window) {
  $scope.initialize = function() {
    $scope.mobile = $scope.isMobile();
    $scope.navigationOpened = !$scope.mobile;
    $window.addEventListener('resize', $scope.onResize);
  }

  $scope.onResize = function(event) {
    if ($scope.mobile == $scope.isMobile()) return;
    $scope.mobile = $scope.isMobile();
    $scope.navigationOpened = !$scope.mobile;
    $scope.$apply();
  }

  $scope.closeNavigation = function() {
    $scope.navigationOpened = false;
  }

  $scope.toggleNavigation = function() {
    $scope.navigationOpened = !$scope.navigationOpened;
  }

  $scope.getNavigationClass = function() {
    return $scope.navigationOpened? "opened" : "";
  }

  $scope.getMainClass = function() {
    return ($scope.navigationOpened && !$scope.mobile)? "shifted" : "";
  }

  $scope.getLinkClass = function(path) {
    return ($location.path().substr(0, path.length) === path)? "active" : "";
  }

  $scope.isMobile = function() {
    return ($window.innerWidth <= 768);
  }

  $scope.isOverlayVisible = function() {
    return $scope.navigationOpened && $scope.mobile;
  }

  $scope.navigate = function(path) {
    $location.path(path);
    if ($scope.mobile) $scope.closeNavigation();
  }

  $scope.initialize();
}
