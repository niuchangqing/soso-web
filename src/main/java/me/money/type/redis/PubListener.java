package me.money.type.redis;

import me.money.type.log.Log;
import redis.clients.jedis.JedisPubSub;

/**
* @author Niucqing
* @email niucqing@gmail.com
* @version 创建时间：2017年3月21日 下午6:48:28
* 类说明
*/
public class PubListener extends JedisPubSub {

	@Override
	public void onMessage(String channel, String message) {
		Log.logSingleLine("onMessage:", channel, message);
	}

	@Override
	public void onPMessage(String pattern, String channel, String message) {
		Log.logSingleLine("onPMessage:", channel, message);
	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		Log.logSingleLine("onSubscribe:", channel, subscribedChannels);
	}

	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		Log.logSingleLine("onPSubscribe:", pattern, subscribedChannels);
	}

}
