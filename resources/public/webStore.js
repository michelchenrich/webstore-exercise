angular.module('webStore', ['ui.router', 'ui.bootstrap']).config(function ($stateProvider, $urlRouterProvider, $httpProvider) {
    $stateProvider
        .state('register', {
            url: '/register',
            templateUrl: '/account/register.html',
            controller: 'registerController'
        })
        .state('webStore', {
            url: '/webStore',
            data: {logInRequired: true},
            templateUrl: '/menu/menu.html',
        })
        .state('webStore.orders', {
            url: '/orders',
            templateUrl: '/underConstruction.html'
        })
        .state('webStore.products', {
            url: '/products',
            templateUrl: '/product/list.html',
            controller: 'productsController'
        })
        .state('webStore.createProduct', {
            url: '/products/create',
            templateUrl: '/product/create.html',
            controller: 'createProductController'
        });
    $urlRouterProvider.otherwise('/webStore');
    $httpProvider.interceptors.push(function ($timeout, $q, $injector) {
        var loginModal, $http, $state;

        $timeout(function () {
            loginModal = $injector.get('loginModal');
            $http = $injector.get('$http');
            $state = $injector.get('$state');
        });

        return {
            responseError: function (rejection) {
                if (rejection.status !== 401)
                    return rejection;

                var deferred = $q.defer();

                loginModal()
                    .then(function () {
                        deferred.resolve($http(rejection.config));
                    })
                    .catch(function () {
                        $state.go('home');
                        deferred.reject(rejection);
                    });

                return deferred.promise;
            }
        };
    });
});