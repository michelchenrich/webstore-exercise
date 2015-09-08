angular.module('webStore').controller('loginController', function ($scope, $http) {
    $scope.form = {email: '', password: ''};
    $scope.status = '';
    $scope.submit = function () {
        $scope.message = 'Logging in...';
        $http.post('/login', $scope.form).then(function (response) {
            if (response.data.success)
                $scope.$close(true);
            else if (response.data.invalidEmailOrPassword) {
                $scope.message = 'Invalid e-mail or password';
                $scope.status = 'has-error';
            }
        });
    };
});