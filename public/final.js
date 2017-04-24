//var walmart = require('walmart')(c3exxssx4eme5j56s5zk7xg7);
//
//walmart.getItem(10449075).then(function(item) {
//  console.log(item.product.productAttributes.productName);
//});

    var app = angular.module('finalProject', []);
    app.controller('finalProjectController', ['$scope', '$http', function($scope, $http){

        $scope.zipStartsWith = ''
        $scope.checkStores = function(){

            var url = ''
            if ($scope.nameStartsWith == ''){
            url = "http://api.walmartlabs.com/v1/stores?format=json&zip=20744&apiKey=c3exxssx4eme5j56s5zk7xg7"

            }
            $http.get(url)
            .then(function(response){
                $scope.characters = response.data.results
            });
        }
        $scope.checkStores()
    }]);