package me.money.type.annotation;

import java.net.URLDecoder;
import java.net.URLEncoder;

import me.money.type.log.Log;

public class Test {
	public static void main(String[] args) throws Exception {

		String encoder = URLEncoder.encode("小黄人呵呵哒哈哈", "utf-8");
		
		String decoder = URLDecoder.decode("小黄人呵呵哒哈哈", "utf-8");
		
		
		Log.log("encoder",encoder);
		Log.log("decoder",decoder);
		
	}

}
