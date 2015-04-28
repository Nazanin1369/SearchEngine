/**
 * Created by nazanin on 4/16/15.
 */

var engine = angular.module("engine", ['ngRoute', 'ngMaterial']);

engine.controller("searchController",['$scope', SearchController]);

engine.config(['$httpProvider', '$mdThemingProvider', '$routeProvider',
    function($routeProvider, $mdThemingProvider) {
        $mdThemingProvider.theme('default');
    }
]);

