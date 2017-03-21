package me.money.type.seckill;

import me.money.type.blockingqueue.SosoQueues;
import me.money.type.log.Log;
import me.money.type.object.pool.JedisPoolMulti;
import redis.clients.jedis.Jedis;

public class SeckillService2 {

	private String name;

	public SeckillService2(String name) {
		this.name = name;
	}

	private static final String key = "count";
	private static final String REDISNAME = "local";

	public boolean doService() {
		Jedis jedis = JedisPoolMulti.get(REDISNAME);
		long reqNum = jedis.incr("reqNum");
		if (reqNum > 30) {
			return false;
		}

		boolean success = false;
		try {
			success = pen();
			if(!success){
				Log.log(name,"failer");
				return false;
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
	
	public boolean pen(){
		return false;
	}

}
