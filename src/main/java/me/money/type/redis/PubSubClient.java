package me.money.type.redis;

import java.util.concurrent.TimeUnit;

import me.money.type.log.Log;
import me.money.type.object.pool.JedisPoolMulti;
import me.money.type.object.pool.Pools;
import me.money.type.utils.TimeStop;
import redis.clients.jedis.Jedis;

/**
* @author Niucqing
* @email niucqing@gmail.com
* @version 创建时间：2017年3月21日 下午6:53:44
* 类说明
*/
public class PubSubClient {

	public static void main(String[] args) {
		PubSubClient pub = new PubSubClient();

		Pools.submit(new Runnable() {

			@Override
			public void run() {
				pub.sub();
			}
		});

		Pools.submit(new Runnable() {

			@Override
			public void run() {
				try {
					pub.pub();
				} catch (InterruptedException e) {
					Log.log(e);
				}
			}
		});

		Log.log("操作完毕");
		TimeStop.second(60 * 10);
	}

	public void sub() {
		Jedis redis = JedisPoolMulti.get("local");
		PubListener pl = new PubListener();
		redis.subscribe(pl, "test1", "test2");
	}

	public void pub() throws InterruptedException {
		Jedis redis = JedisPoolMulti.get("local");
		for (int i = 0; i < 100; i++) {
			TimeUnit.SECONDS.sleep(1);
			redis.publish("test1", "test1" + i);
			redis.publish("test2", "test2" + i);
		}
	}

}
