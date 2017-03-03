package me.money.type.kafka;

import me.money.type.utils.TimePause;

public class KafkaCon {
	public static void main(String[] args) {

		KConsumer kc = new KConsumer("test", "niucqing");
		kc.start();

		TimePause.pause(1000);
	}

}
