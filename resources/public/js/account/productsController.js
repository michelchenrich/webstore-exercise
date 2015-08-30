angular.module('webStore').controller('productsController', function ($scope, $http) {
    $scope.hasProducts = false;
    $scope.products = [];
    $http.get('/products').then(function (response) {
        if (response.data.length == 0)
            return;
        $scope.products = response.data;
        $scope.hasProducts = true;
    });
});