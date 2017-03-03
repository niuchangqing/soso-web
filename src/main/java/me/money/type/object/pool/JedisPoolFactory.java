package me.money.type.object.pool;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;

import redis.clients.jedis.Jedis;

public class JedisPoolFactory implements PooledObjectFactory<Jedis> {
	private PropertiesConfiguration pconf;

	public JedisPoolFactory() {
		// TODO Auto-generated constructor stub
	}

	public JedisPoolFactory(PropertiesConfiguration pconf) {
		// TODO Auto-generated constructor stub
		this.pconf = pconf;
	}

	@Override
	public void activateObject(PooledObject<Jedis> arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroyObject(PooledObject<Jedis> arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public PooledObject<Jedis> makeObject() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void passivateObject(PooledObject<Jedis> arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validateObject(PooledObject<Jedis> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
