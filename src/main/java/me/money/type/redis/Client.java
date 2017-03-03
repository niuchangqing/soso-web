package me.money.type.redis;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Jedis redis = new Jedis("121.201.62.48", 6777);
		
		List<String> hvals = redis.hvals("open-crowd-user-name");
		for (String v : hvals) {
			System.out.println(v);
		}
		String key = "open-crowd-user-name";
		Set<String> hkeys = redis.hkeys("open-crowd-user-name");
		System.out.println(hkeys);
	}

}
