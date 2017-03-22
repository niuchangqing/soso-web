package me.money.type.redis;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.javatuples.Pair;

import me.money.type.log.Log;
import me.money.type.object.pool.JedisPoolMulti;
import me.money.type.object.pool.Pools;
import me.money.type.utils.TimeStop;
import redis.clients.jedis.Jedis;

public class LockTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		while (true) {
			TimeStop.millSeconds(200);
			Pools.submit(new Runnable() {
				@Override
				public void run() {
					Pair<Boolean, Map<String, Object>> lockdd = lockdd(22222, 10);
					Log.log(lockdd.getValue0(),lockdd.getValue1());
				}
			});
		}
	}
	
	public static Map<String, Object> getMsg(Map<String, Object> map, long ttl) {
		map.put("msg", String.format("Tips.主播能量释放中请%s秒后再送出", ttl));
		map.put("waitseconds", ttl);
		map.put("state", 11);
		return map;
	}

	public static Pair<Boolean, Map<String, Object>> lockdd(long anchorId, int seconds) {
		Map<String, Object> map = new HashMap<String, Object>();

		String lockkey = "qmeng-lock-" + anchorId;
		long now = System.currentTimeMillis();

		Jedis redis = JedisPoolMulti.get("local");
		try {
			long status = redis.setnx(lockkey, now + "");
			if (status == 0) {
				String lastKeyValue = redis.get(lockkey);
				long ttl = redis.ttl(lockkey);
				if (ttl > 0) {// 有失效时间，则未拿到锁
					map.put("msg", String.format("主播能量释放中请%s秒后再送出", ttl));
					map.put("waitseconds", ttl);
					map.put("state", 11);
					return Pair.with(false, map);
				} else {
					String currentLock = redis.getSet(lockkey, now + "");
					if (StringUtils.isEmpty(currentLock)) {
						// 拿到锁
					} else {// 未拿到锁
						if (!StringUtils.equals(currentLock, lastKeyValue)) {
							map.put("msg", String.format("主播能量释放中请%s秒后再送出", ttl));
							map.put("waitseconds", ttl);
							map.put("state", 2);
							return Pair.with(false, map);
						}
					}
				}
			}
			redis.expire(lockkey, seconds);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			if (null != redis) {
				JedisPoolMulti.returnSource("local", redis);
			}
		}
		return Pair.with(true, map);
	}
}
