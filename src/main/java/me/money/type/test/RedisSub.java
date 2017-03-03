package me.money.type.test;

import redis.clients.jedis.BinaryJedisPubSub;

public class RedisSub extends BinaryJedisPubSub{
	@Override
	public void onMessage(byte[] channel, byte[] message) {
		// TODO Auto-generated method stub
		super.onMessage(channel, message);
	}
	
	
}
