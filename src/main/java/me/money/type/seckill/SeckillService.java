package me.money.type.seckill;

import me.money.type.blockingqueue.SosoQueues;
import me.money.type.log.Log;
import me.money.type.object.pool.JedisPoolMulti;
import redis.clients.jedis.Jedis;

public class SeckillService implements Runnable {

	private String name;

	public SeckillService(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.doService();
	}

	private static final String key = "count";
	private static final String REDISNAME = "local";

	public boolean doService() {
		Jedis jedis = JedisPoolMulti.get(REDISNAME);
		boolean success = false;
		try {
			long value = jedis.decr(key);
			if (value < 0) {
				jedis.incr(key);
//				Log.logSingleLine(name, "failer");
				return success;
			}

			Log.log(name, "success！");
			jedis.incr("success");
			SosoQueues.put(name);
		} catch (Exception e) {
			Log.log(e);
		} finally {
			JedisPoolMulti.returnSource(REDISNAME, jedis);
		}

		return true;
	}

}
