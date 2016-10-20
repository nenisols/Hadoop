'use strict'

angular.module('hdpconsoleApp')
	.controller('TopicController', ['$scope','TopicService',function($scope,TopicService){
		
		//define the model 
		$scope.topic = {
				name:"test",
				partitions:1,
				replications:1,
		};
		
		/*
		 * create the new topic
		 * @topic: input argument to create the topic
		 */
		$scope.createTopic = function(topic){
			
			TopicService.createTopic().then(function(res){
				
			},
			function(error){
				
			});
			
		};
		
		/*
		 * delete the topic
		 * @topic_nm : name of the topic to mark for deletion
		 */
		$scope.deleteTopic = function(topic_nm){
			
			TopicService.deleteTopic(topic_nm).then(function(res){
				
			},
			function(error){
				
			});			
			
		};
		
		/*
		 * show all the topics
		 */
		$scope.showTopics= function(){
			
			TopicService.showallTopics().then(function(res){
				
			},
			function(err){
				
			});
		};
		
	}]);