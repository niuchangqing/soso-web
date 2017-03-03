package me.money.type.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KProducer extends Thread {
	private final KafkaProducer<Integer, String> producer;

	private final String topic;

	public KProducer(String topic, boolean isAsync) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "120.132.38.213:9199");
		props.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("client.id", "niucqing-testd");
		props.put("metadata.broker.list","120.132.38.213:9199");
		props.put("auto.create.topics.enable", true);
		
//		props.put("broker.id", "120.132.38.213:9199");
		props.put("timeout", 50);
		this.topic = topic;

		producer = new KafkaProducer<Integer, String>(props);
	}

	@Override
	public void run() {
		int messageNo = 1;
		while (true) {
			String messageStr = new String("Message_" + messageNo);
			try {
				producer.send(new ProducerRecord("test", messageNo, messageStr)).get();
				System.err.println("Send:" + messageStr);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			messageNo++;
			if(messageNo>1000){
				break;
			}
		}
	}

}
