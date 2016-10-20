'use strict';

function Environment(env_name){
	if('lab'){
		this.kafka_broker_ip = 'dtvcbddv98.corp.cox.com:6667';
		this.zookeeper = 'dtvcbddv98.corp.cox.com:2181';
		
	}
	else if('dev'){
		this.kafka_broker_ip = 'dtvcbddv98.corp.cox.com:6667';
		this.zookeeper = 'dtvcbddv98.corp.cox.com:2181';
	}
	else{
		this.kafka_broker_ip = 'dtvcbddv98.corp.cox.com:6667';
		this.zookeeper = 'dtvcbddv98.corp.cox.com:2181';
	}
};

angular.module('hdpconsoleApp')
		.service('environment',['env_name', Environment]);