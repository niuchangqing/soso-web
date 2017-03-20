package me.money.type.guava;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import me.money.type.log.Log;

public class UserCatch {

	private static LoadingCache<String, String> cache;
	private static CacheLoader<String, String> loader;

	public UserCatch() {
		loader = new CacheLoader<String, String>() {
			@Override
			public String load(String key) throws Exception {

				return fresh(key);
			}
		};
		/**
		 * expireAfterAccess(long, TimeUnit)  这个方法是根据某个键值对最后一次访问之后多少时间后移除
		　　 expireAfterWrite(long, TimeUnit)  这个方法是根据某个键值对被创建或值被替换后多少时间移除
		
			refresh机制：
		　　LoadingCache.refresh(K)  在生成新的value的时候，旧的value依然会被使用。
		　　CacheLoader.reload(K, V) 生成新的value过程中允许使用旧的value
		　　CacheBuilder.refreshAfterWrite(long, TimeUnit) 自动刷新cache
		 */
		//		cache = CacheBuilder.newBuilder().refreshAfterWrite(1, TimeUnit.MINUTES).build(loader);
		//		cache = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).build(loader);
		cache = CacheBuilder.newBuilder().refreshAfterWrite(1, TimeUnit.MINUTES).expireAfterWrite(20, TimeUnit.SECONDS)
				.build(loader);
	}

	public static String fresh(String key) {
		Log.log("refresh...");
		return "hello-" + new Random().nextInt(100);
	}

	public String get(String key) throws ExecutionException {
		return cache.get(key);
	}

	public static void main(String[] args) throws Exception {
		UserCatch user = new UserCatch();
		while (true) {
			for (int i = 0; i < 10; i++) {
				TimeUnit.SECONDS.sleep(1);
				String v = user.get(i + "");
				Log.logSingleLine(i, "=", v);
			}
		}

	}

}
