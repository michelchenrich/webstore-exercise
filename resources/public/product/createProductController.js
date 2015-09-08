angular.module('webStore').controller('createProductController', function ($scope, $http, $state) {
    $scope.form = {name: '', description: '', price: '', unitsInStock: 0};
    $scope.messages = {form: '', name: '', description: '', price: '', unitsInStock: ''};
    $scope.status = {name: '', description: '', price: '', unitsInStock: ''};
    $scope.submit = function () {
        $scope.messages.form = 'Creating product...';
        $http.post('/products', $scope.form).then(function (response) {
            $scope.messages.form = '';
            if (response.data.success)
                $state.go('webStore.products');
            else {
                $scope.status.name = response.data.invalidName ? 'has-error' : '';
                $scope.messages.name = response.data.invalidName ? 'Invalid name, it cannot be blank' : '';
                $scope.status.description = response.data.invalidDescription ? 'has-error' : '';
                $scope.messages.description = response.data.invalidDescription ? 'Invalid description, it cannot be blank' : '';
                $scope.status.price = response.data.invalidPrice ? 'has-error' : '';
                $scope.messages.price = response.data.invalidPrice ? 'Invalid price, it must be greater than zero' : '';
                $scope.status.unitsInStock = response.data.invalidUnitsInStock ? 'has-error' : '';
                $scope.messages.unitsInStock = response.data.invalidUnitsInStock ? 'Invalid number of units, it must be positive' : '';
            }
        });
    };
});