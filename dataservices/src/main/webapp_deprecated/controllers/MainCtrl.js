

function MainCtrl($scope){
	
	this.appTitle = 'Hadoop Console Application';
	this.show = function(){
		this.show = "test";
	};
}

angular.module('hdpconsoleApp')
		.controller('MainCtrl', MainCtrl);
MainCtrl.$inject = ['$scope'];