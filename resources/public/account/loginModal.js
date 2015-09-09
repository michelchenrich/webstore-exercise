angular.module('webStore').service('loginModal', function ($modal, $rootScope) {
    return function () {
        return $modal
            .open({
                templateUrl: 'account/loginModal.html',
                controller: 'loginController'
            })
            .result
            .then(function (loggedIn) {
                $rootScope.loggedIn = loggedIn;
                return loggedIn;
            });
    };
});