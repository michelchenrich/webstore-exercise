angular.module('webStore').controller('registerController', function ($scope, $http, loadSession, $state) {
    $scope.form = {email: '', password: '', passwordConfirmation: ''};
    $scope.messages = {form: '', email: '', password: '', passwordConfirmation: ''};
    $scope.status = {email: '', password: '', passwordConfirmation: ''};
    $scope.submit = function () {
        $scope.messages.form = 'Registering...';
        $http.post('/register', $scope.form).then(function (response) {
            $scope.messages.form = '';
            if (response.data.success) 
                loadSession().then(function () { $state.go('webStore'); });
            else {
                $scope.status.email = response.data.invalidEmail ? 'has-error' : '';
                $scope.messages.email = response.data.invalidEmail ? 'Invalid e-mail, please use the following format: email@host.com' : '';
                $scope.status.password = response.data.invalidPassword ? 'has-error' : '';
                $scope.messages.password = response.data.invalidPassword ? 'Invalid password, it must contain at least 8 characters, with at least one upper case letter, a lower case letter, and a number' : '';
                $scope.status.passwordConfirmation = response.data.invalidPasswordConfirmation ? 'has-error' : '';
                $scope.messages.passwordConfirmation = response.data.invalidPasswordConfirmation ? 'Invalid password confirmation, it must match the password' : '';
            }
        });
    };
});
