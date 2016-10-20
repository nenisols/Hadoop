package hdp.test;

import hdp.websocket.test.ApiResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController{

	@Autowired
	private SimpMessagingTemplate template;

	private KafkaMsgProducer producer;
	private Thread kcthread;

	public MainController(KafkaMsgProducer producer){
		this.producer = producer;
	}
	/**
	  * send message to kafka topic
	**/
	@RequestMapping(value="/sendtokafka", method=RequestMethod.PUT)
	@SendTo("/topic/apiresponse")
	public ResponseEntity<String> sendMessagetoKafka(@RequestBody KafkaMsg msg)
	{
		int ret = this.producer.sendMessage(msg.getTopic(),msg.getMsg());

		if(ret == 1)
			return new ResponseEntity<String>("message sent success.",HttpStatus.OK);
		else
		return new ResponseEntity<String>("message sent failed.",HttpStatus.OK);
	}
	@RequestMapping(value="/testwebs", method=RequestMethod.PUT)
	@SendTo("/topic/apiresponse")
	public ApiResponse testwebs(@RequestBody KafkaMsg msg)
	{
		//fireResponse("fire form test");
		return new hdp.websocket.test.ApiResponse("test success");
	}
	@RequestMapping(value="/subscribetokafka",method=RequestMethod.PUT)
	@SendTo("/topic/apiresponse")
	public ResponseEntity<String> subscribeToKafkaTopic(@RequestBody String topic){

		if(kcthread == null){
			KafkaMsgConsumer consumer = new KafkaMsgConsumer(this);
			List<String> topicNames = new ArrayList<String>();
			topicNames.add("test_j_topic");
			
			
			consumer.setTopicsList(topicNames);
			kcthread = new Thread(consumer);
			kcthread.start();
		}
		return new ResponseEntity<String>("subscribed success.",HttpStatus.OK);
	}

	@RequestMapping(value="/unsubscribetokafka", method=RequestMethod.PUT)
	public ResponseEntity<String> unsubscribetoKafkaTopic(){
		if(kcthread != null){
			kcthread.interrupt();
		}
		return new ResponseEntity<String>("un subscribe success", HttpStatus.OK);
		//return "un subscribe success.";
	}
	public void fireResponse(String res){
		template.convertAndSend("/topic/apiresponse", new hdp.websocket.test.ApiResponse(res));
	}

}