package me.money.type.object.pool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolFactory {

	private static final String HOST = "121.201.62.48";
	private static final int PORT = 6777;

	private static JedisPool pool;

	static {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxWaitMillis(1000);
		config.setMaxIdle(100);
		config.setMinIdle(10);
		config.setMaxTotal(1000);
		pool = new JedisPool(config, HOST, PORT);

		System.out.println();
	}

	public static Jedis get() {
		return pool.getResource();
	}

	public static void returnResource(Jedis resource) {
		if (null != resource)
			pool.returnResource(resource);
	}
}
