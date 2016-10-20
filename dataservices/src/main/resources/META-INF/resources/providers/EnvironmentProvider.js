'use strict';

angular.module('hdpconsoleApp')
		.provider('environment',function EnvironmentProvider(){
			
			this.$get = ['env_name',function environmentFactory(env_name){
				
				return new Environment(env_name);
			}];
		});