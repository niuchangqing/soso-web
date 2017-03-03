package me.money.type.kafka;

public class KafkaProperties {
	public final static String zkConnect = "120.132.38.213:21812";
	public final static String groupId = "group1";
	public final static String topic = "topic1";
	public final static String kafkaServerURL = "120.132.38.213";
	public final static int kafkaServerPort = 9199;
	public final static int kafkaProducerBufferSize = 64 * 1024;
	public final static int connectionTimeOut = 20000;
	public final static int reconnectInterval = 10000;
	public final static String topic2 = "topic2";
	public final static String topic3 = "topic3";
	public final static String clientId = "SimpleConsumerDemoClient";

	private KafkaProperties() {

	}
}
