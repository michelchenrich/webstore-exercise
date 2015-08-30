angular.module('webStore').controller('loginController', function ($scope, $http, loadSession) {
    $scope.form = {email: '', password: ''};
    $scope.status = '';
    $scope.submit = function () {
        $scope.message = 'Logging in...';
        $http.post('/login', $scope.form).then(function (response) {
            if (response.data.success)
                loadSession();
            else if (response.data.invalidEmailOrPassword) {
                $scope.message = 'Invalid e-mail or password';
                $scope.status = 'has-error';
            }
        });
    };
});