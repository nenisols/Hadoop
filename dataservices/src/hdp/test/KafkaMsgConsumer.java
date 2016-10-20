package hdp.test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import kafka.consumer.ConsumerThreadId;
import kafka.javaapi.consumer.ConsumerRebalanceListener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class KafkaMsgConsumer implements Runnable{

	private MainController listener;
	private List<String> topicsList;
	public KafkaMsgConsumer(MainController listener){
		this.listener = listener;
	}

	public void setTopicsList(List<String> topicsList){
		this.topicsList = topicsList;
	}
	@Override
	public void run(){

		Properties props = new Properties();
		props.put("bootstrap.servers","dvtcbddv97.corp.cox.com:6667");
		props.put("group.id","test2");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        //props.put("security.protocol","SASL_PLAINTEXT");
        props.put("enable.auto.commit",true);
        props.put("auto.offset.reset","latest");
       /* props.put("session.timeout.ms",3000);
        props.put("request.timeout.ms",4000);
        props.put("heartbeat.interval.ms",1000);*/

        listener.fireResponse("------ kafka consumer----- INPROCESS------");
        
        final KafkaConsumer<String,byte[]>consumer = new KafkaConsumer<String,byte[]>(props);
        class HandleRebalance implements ConsumerRebalanceListener, org.apache.kafka.clients.consumer.ConsumerRebalanceListener { 
		    public void onPartitionsAssigned(Collection<TopicPartition> partitions) { 
		    	for(TopicPartition partition: partitions){
		    		System.out.println("Consumer Rebalance Listener:" + partition.partition() + ":topic:" +  partition.topic());
		    	}
		    }

		    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
		    	System.out.println("onpartition revoked");
		        //consumer.commitSync(currentOffsets); 
		    }

			@Override
			public void beforeReleasingPartitions(
					Map<String, Set<Integer>> partitionOwnership) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeStartingFetchers(
					String consumerId,
					Map<String, Map<Integer, ConsumerThreadId>> globalPartitionAssignment) {
				// TODO Auto-generated method stub
				
			}
			
		}
		/*final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Starting exit...");
                // Note that shutdownhook runs in a separate thread, so the only thing we can safely do to a consumer is wake it up
                consumer.wakeup();
                try {
                    mainThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });*/

        boolean running = true;

        try{
        	consumer.subscribe(topicsList,new HandleRebalance());
        	consumer.poll(0);
        	listener.fireResponse("------ kafka consumer----- stared------");
        	while(running){
        		ConsumerRecords<String, byte[]> records = consumer.poll(100);
        		for(ConsumerRecord<String,byte[]> record:records){
        			String result = String.format("topic = %s, partition = %s, offset = %d, key = %s\n",
            		          record.topic(), record.partition(), record.offset(), record.key());
        			listener.fireResponse(result);
        			//template.convertAndSend("/topic/apiresponse", new KafkaMsg(record.topic,result));

        		}
        	}
        }        
        finally{
        	consumer.close();
        	listener.fireResponse("------ kafka consumer----- stopped------");
        }
		
	}

	public int stop(){
		return 1;
	}
}