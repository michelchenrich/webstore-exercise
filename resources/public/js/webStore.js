angular.module('webStore', ['ngRoute']).config(function ($routeProvider) {
    $routeProvider
        .when('/', {templateUrl: 'partials/products.html', controller: 'productsController'})
        .when('/login', {templateUrl: '/partials/login.html', controller: 'loginController'})
        .when('/register', {templateUrl: '/partials/register.html', controller: 'registerController'})
        .otherwise({redirectTo: '/'});
});