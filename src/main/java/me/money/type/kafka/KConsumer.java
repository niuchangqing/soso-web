package me.money.type.kafka;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KConsumer extends Thread {
	private final String topic;
	private final KafkaConsumer<Integer, String> consumer;
	private final String group;

	public KConsumer(String topic, String group) {
		this.topic = topic;
		this.group = group;

		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "120.132.38.213:9199");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, this.group);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.IntegerDeserializer");
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");

		consumer = new KafkaConsumer(props);
	}

	@Override
	public void run() {
		consumer.subscribe(Collections.singletonList(this.topic));
		while(true){
			ConsumerRecords<Integer, String> records = consumer.poll(1000);
			for (ConsumerRecord<Integer, String> consumerRecord : records) {
				int key = consumerRecord.key();
				String v = consumerRecord.value();
				System.out.println(key + "--" + v);
			}
		}
		
	}
}
