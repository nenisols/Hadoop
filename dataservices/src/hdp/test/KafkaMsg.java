package hdp.test;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


public class KafkaMsg{
	private String topic;

	private String msg;

	/*public KafkaMsg(String topic, String msg){
		this.topic = topic;
		this.msg = msg;
	}*/
	public String getTopic(){
		return this.topic;
	}
	public String getMsg(){
		return this.msg;
	}
	/*@Override
	public boolean equals(Object that){
		return EqualsBuilder.reflectionEquals(this,that,"topic","msg");
	}
	@Override
	public int hashCode(){
		return HashCodeBuilder.reflectionHashCode(this,"topic","msg");
	}*/
}