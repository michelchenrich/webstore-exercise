angular.module('webStore').factory('loadSession', function ($rootScope, $http, $state) {
    return function () {
        return $http.get('/read-user')
            .then(function (response) {
                $rootScope.loggedIn = response.data.success;
                if (!$rootScope.loggedIn)
                    $state.go('register');
            });
    };
});