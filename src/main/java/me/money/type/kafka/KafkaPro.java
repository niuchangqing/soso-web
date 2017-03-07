package me.money.type.kafka;

import me.money.type.utils.TimeStop;

public class KafkaPro {

	public static void main(String[] args) {
		KProducer sc = new KProducer(KafkaProperties.topic2, false);
		sc.start();

		TimeStop.pause(100);
	}

}
