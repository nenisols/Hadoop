'use strict';

angular.module('hdpconsoleApp')
		.factory('topicService',['$log','$http','$q','EnvironmentProvider',function($log,$http,$q,EnvironmentProvider){
			
			return{
				createTopic : function(){
					return $http.get('http://localhost:8080/topic/create/')
								.then(
										function(response){
											
										},
										function(response){
											
										}
									);
				},
				deleteTopic: function(){
					return $http.get('http://localhost:8080/topic/delete')
								.then(
										function(response){
											
										},
										function(response){
											
										}
									);
				},
				showallTopics: function(){
					return $http.get('http://localhost:8080/topic/delete')
					.then(
							function(response){
								
							},
							function(response){
								
							}
						);
				}
	
			};		
			
		}]);