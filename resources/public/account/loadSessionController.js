angular.module('webStore').controller('loadSessionController', function ($rootScope, $state, $http) {
    $http.get('/read-user').then(function (response) {
        $rootScope.loggedIn = response.data.success;
        if ($rootScope.loggedIn)
            $state.go('webStore');
        else
            $state.go('register');
    });
});