package me.money.type.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

import me.money.type.log.Log;
import me.money.type.object.pool.JedisPoolMulti;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public class Client {
	private static final String REDIS_NAME = "local";

	public static void main(String[] args) {
		long[] anchorIds = new long[100];
		for (int i = 0; i < 100; i++) {
			anchorIds[i] = i;
		}

		fansCount(anchorIds);

	}

	public static Map<Long, Long> fansCount(long... anchorIds) {
		Map<Long, Long> rs = new HashMap<Long, Long>();
		Jedis redis = JedisPoolMulti.get(REDIS_NAME);
		Pipeline pipelined = redis.pipelined();
		try {
			Map<Long, Response<Long>> scards = new HashMap<Long, Response<Long>>();
			Stopwatch sw = Stopwatch.createStarted();
			for (int i = 0; i < anchorIds.length; i++) {
				long anchorId = anchorIds[i];
				scards.put(anchorId, pipelined.scard("a" + anchorId));
			}
			pipelined.sync();

			for (Entry<Long, Response<Long>> entry : scards.entrySet()) {
				rs.put(entry.getKey(), entry.getValue().get());
			}

			long ms = sw.elapsed(TimeUnit.MILLISECONDS);
			Log.logSingleLine("use", ms, "ms");
		} catch (Exception e) {
			Log.log("取消关注异常：", e);
		} finally {
			if (redis != null) {
				JedisPoolMulti.returnSource(REDIS_NAME, redis);
			}
		}
		Log.log(rs);
		return rs;
	}

	public static Map<Long, Long> fansCounts(long... anchorIds) {
		Map<Long, Long> rs = new HashMap<Long, Long>();
		Jedis redis = JedisPoolMulti.get(REDIS_NAME);
		try {
			Stopwatch sw = Stopwatch.createStarted();
			for (int i = 0; i < anchorIds.length; i++) {
				long anchorId = anchorIds[i];
				rs.put(anchorId, redis.scard("a" + anchorId));
			}
			long ms = sw.elapsed(TimeUnit.MILLISECONDS);
			Log.logSingleLine("use", ms, "ms");
		} catch (Exception e) {
			Log.log("取消关注异常：", e);
		} finally {
			if (redis != null) {
				JedisPoolMulti.returnSource(REDIS_NAME, redis);
			}
		}
		Log.log(rs);
		return rs;
	}

}
