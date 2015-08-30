angular.module('webStore').controller('createProductController', function ($scope, $http, $location) {
    $scope.form = {name: '', description: '', price: 0.0, unitsInStock: 0};
    $scope.messages = {form: '', name: '', description: '', price: '', unitsInStock: ''};
    $scope.status = {name: '', description: '', price: '', unitsInStock: ''};
    $scope.submit = function () {
        $scope.messages.form = 'Creating product...';
        $http.post('/product/create', $scope.form).then(function (response) {
            $scope.messages.form = '';
            $location.path('/');
        });
    };
});