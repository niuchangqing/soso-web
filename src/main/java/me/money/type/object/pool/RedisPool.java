package me.money.type.object.pool;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;

public class RedisPool {

	private GenericObjectPool<Jedis> pool;

	public static void main(String[] args) {
	}

	public PropertiesConfiguration getConfigProperties() throws ConfigurationException {
		PropertiesConfiguration config = new PropertiesConfiguration("redis.json");
		return config;
	}

	public void init() throws ConfigurationException {
		PropertiesConfiguration pconf = getConfigProperties();
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxIdle(pconf.getInt("maxIdel"));

		JedisPoolFactory factory = new JedisPoolFactory();

		pool = new GenericObjectPool<Jedis>(factory, config);
	}

	public void get() throws Exception {
		pool.borrowObject();
	}

	public void returnSource(Jedis jedis) {
		pool.returnObject(jedis);
	}
}
