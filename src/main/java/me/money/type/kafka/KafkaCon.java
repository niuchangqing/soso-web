package me.money.type.kafka;

import me.money.type.utils.TimeStop;

public class KafkaCon {
	public static void main(String[] args) {

		KConsumer kc = new KConsumer("test", "niucqing");
		kc.start();

		TimeStop.pause(1000);
	}

}
