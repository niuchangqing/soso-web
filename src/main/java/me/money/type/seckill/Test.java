package me.money.type.seckill;

import me.money.type.blockingqueue.SosoQueues;
import me.money.type.log.Log;
import me.money.type.object.pool.Pools;
import me.money.type.utils.TimeStop;

public class Test {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log.log("go----");

		Pools.submit(new Runnable() {

			@Override
			public void run() {
				while (true) {
					String data = SosoQueues.take();
					Log.log("队列获得数据：", data);
				}
			}
		});

		int c = 0;
		while (c < 100) {
			c++;
			for (int i = 0; i < 10000; i++) {
				Pools.submit(new SeckillService("T" + i));
			}
			TimeStop.second(10);
		}
	}

}
