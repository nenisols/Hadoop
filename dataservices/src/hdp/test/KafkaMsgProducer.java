package hdp.test;

import org.springframework.stereotype.Component;

@Component
public class KafkaMsgProducer{

	public int sendMessage(String topic, String msg){
		return 0;
	}
	public int sendMessage(String topic, Byte[] msg){
		return 0;
	}
}