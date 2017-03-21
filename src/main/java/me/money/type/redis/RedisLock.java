package me.money.type.redis;

import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import me.money.type.log.Log;
import me.money.type.object.pool.JedisPoolMulti;
import me.money.type.object.pool.Pools;
import me.money.type.utils.TimeStop;
import redis.clients.jedis.Jedis;

public class RedisLock {

	public static void main(String[] args) {
		for (int i = 0; i < 100000; i++) {
			Pools.submit(new mytask("T" + i));
//			if (i % 20 == 0)
//				TimeStop.millSeconds(500);
		}
	}
}

class mytask implements Runnable {

	private static final String REDISNAME = "local";
	private Jedis jedis = JedisPoolMulti.get(REDISNAME);

	private static final String LOCK = "MYLOCK";
	private static final long TIMEOUT = 5 * 1000;

	private String name;

	public mytask(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		long now = System.currentTimeMillis();
		String nowS = String.valueOf(now);
		long status = jedis.setnx(LOCK, nowS);
		if (status == 0) {
			String lockValue = jedis.get(LOCK);
			long longLock = StringUtils.isEmpty(lockValue) ? -1 : Long.valueOf(lockValue);
			long x = now - longLock;
			if (x < TIMEOUT) {
				//				Log.logSingleLine(name, "未拿到锁,直接退出");
				return;
			}
			String currentLock = jedis.getSet(LOCK, nowS);
			if (StringUtils.isEmpty(currentLock)) {

			} else {
				if (!StringUtils.equals(currentLock, lockValue)) {
					return;
				}
				Log.log(getTime(), name, "拿到新锁", "死锁失效");
			}
		}

		try {
			int c = new Random().nextInt(10);
			Log.log(getTime(), name, "do something ... will need", c, "s");

			TimeStop.second(c);

			//			Log.log(name, "执行完毕");
		} catch (NumberFormatException e) {

		} finally {
			long x = System.currentTimeMillis() - now;
			if (x < TIMEOUT) {
				jedis.del(LOCK);
			}
		}

	}

	private String getTime() {
		return DateTime.now().toString("yyyy-MM-dd hh:mm:ss");
	}
}