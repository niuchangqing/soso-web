package me.money.type.object.pool;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;

import me.money.type.log.Log;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolMulti {
	private static Map<String, JedisPool> map = new ConcurrentHashMap<String, JedisPool>();

	static {
		XMLConfiguration config = null;
		try {
			config = new XMLConfiguration("redis.xml");
		} catch (ConfigurationException e) {
			Log.log(e);
		}
		List<ConfigurationNode> children = config.getRoot().getChildren();
		for (ConfigurationNode node : children) {
			List<ConfigurationNode> lines = node.getChildren();

			RedisBean bean = new RedisBean();

			for (ConfigurationNode line : lines) {
				String nodeName = line.getName();
				String value = line.getValue().toString();

				if ("ip".equals(nodeName)) {
					bean.setIp(value);
				} else if ("port".equals(nodeName)) {
					bean.setPort(Integer.valueOf(value));
				} else if ("name".equals(nodeName)) {
					bean.setName(value);
				} else if ("maxidle".equals(nodeName)) {
					bean.setMaxidle(Integer.valueOf(value));
				} else if ("minidle".equals(nodeName)) {
					bean.setMinidle(Integer.valueOf(value));
				} else if ("maxwaitmillis".equals(nodeName)) {
					bean.setMaxwaitmillis(Integer.valueOf(value));
				} else if ("maxtotal".equals(nodeName)) {
					bean.setMaxtotal(Integer.valueOf(value));
				} else if ("describe".equals(nodeName)) {
					bean.setDescribe(value);
				} else if ("testonborrow".equals(nodeName)) {
					bean.setTestonborrow(Boolean.valueOf(value));
				}
			}
			Log.log(bean);
			create(bean);

			Log.log(bean.getDescribe(), " created...");
		}

	}

	private static void create(RedisBean bean) {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxWaitMillis(bean.getMaxwaitmillis());
		config.setMaxIdle(bean.getMaxidle());
		config.setMinIdle(bean.getMinidle());
		config.setMaxTotal(bean.getMaxtotal());
		config.setTestOnBorrow(bean.isTestonborrow());
		config.setTestOnReturn(true);
		JedisPool pool = new JedisPool(config, bean.getIp(), bean.getPort());
		map.put(bean.getName(), pool);
	}

	public static Jedis get(String name) {
		if (!map.containsKey(name))
			throw new RuntimeException("未找到名称为[" + name + "]的redis连接池");
		return map.get(name).getResource();
	}

	public static void returnSource(String name, Jedis jedis) {
		if (!map.containsKey(name))
			throw new RuntimeException("未找到名称为[" + name + "]的redis连接池");
		if (null != jedis)
			map.get(name).returnResource(jedis);
		// System.err.println("连接返还");
	}

}

class RedisBean {
	private int port;
	private String ip;
	private String name;
	private int maxidle;
	private int minidle;
	private int maxwaitmillis;
	private int maxtotal;
	private String describe;
	private boolean testonborrow;

	private static final String lineSeparator = System.lineSeparator();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		sb.append("ip:").append(ip).append(lineSeparator).append("port:").append(port).append(lineSeparator)
				.append("name:").append(name);
		return sb.toString();
	}

	public boolean isTestonborrow() {
		return testonborrow;
	}

	public void setTestonborrow(boolean testonborrow) {
		this.testonborrow = testonborrow;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxidle() {
		return maxidle;
	}

	public void setMaxidle(int maxidle) {
		this.maxidle = maxidle;
	}

	public int getMinidle() {
		return minidle;
	}

	public void setMinidle(int minidle) {
		this.minidle = minidle;
	}

	public int getMaxwaitmillis() {
		return maxwaitmillis;
	}

	public void setMaxwaitmillis(int maxwaitmillis) {
		this.maxwaitmillis = maxwaitmillis;
	}

	public int getMaxtotal() {
		return maxtotal;
	}

	public void setMaxtotal(int maxtotal) {
		this.maxtotal = maxtotal;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
}
