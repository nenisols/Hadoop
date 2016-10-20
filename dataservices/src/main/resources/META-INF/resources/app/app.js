

angular.module('hdpconsoleApp',['ui.router'])
		.config(function($stateProvider, $urlRouterProvider,$locationProvider){
			
			$urlRouterProvider.otherwise("/default");
			
			$stateProvider.state('default',{
				url: '/home',
				templateUrl: "views/home.html",
				controller: "HomeCtrl"
				
			})
			.state('topic',{
				url: '/topic',
				templateUrl: "views/topic.html",
				controller: "TopicCtrl"
			})
			.state('overview',{
				url: '/overview',
				templateUrl: "views/overview.html",
				controller: "OverviewCtrl"
			})
			.state('kafka',{
				url: '/kafka',
				templateUrl: "views/kafka.html",
				controller: "KafkaCtrl"
			})
			.state('spark',{
			    url: '/spark',
			    templateUrl: "views/spark.html",
			    controller: "SparkCtrl"
			})
			.state('database',{
			    url: '/database',
			    templateUrl: "views/database.html",
			    controller: "SparkCtrl"
			});
			//$locationProvider.html5Mode(true);
		});
