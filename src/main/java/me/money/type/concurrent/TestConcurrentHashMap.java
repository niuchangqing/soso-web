package me.money.type.concurrent;

import java.util.concurrent.ConcurrentHashMap;

/**
* @author Niucqing
* @email niucqing@gmail.com
* @version 创建时间：2017年3月22日 上午11:51:36
* 类说明
*/
public class TestConcurrentHashMap {

	public static volatile int sum = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
		map.put("a","b");
		map.get("a");
		String s = "";
		s.hashCode();
	}

}
