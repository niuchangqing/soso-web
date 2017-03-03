package me.money.type.request;

import java.io.IOException;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Req {

	//众筹状态
	 private static String url = "http://plugin.client.qxiu.com/qmeng/pay/crowd/info";
	//调用设备
//	private static String url = "http://123.56.124.20/qingmeng-PHP/index.php?m=Api&c=User&a=handle";
//	private static String url = "http://plugin.client.qxiu.com/qmeng/anchor/bePenRank";

	public static void main(String[] args) throws IOException {
		url = setParam();
		System.out.println(url);

		call(url);
		

	}

	public static void call(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request req = new Request.Builder().url(url).build();
		Response res = client.newCall(req).execute();
		if (res.isSuccessful()) {
			String json = res.body().string();
			formatJson(json);
		} else {
			System.err.println("error" + res.body().string());
		}
	}

	public static void formatJson(String json) {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(json);
		String format = g.toJson(je);
		System.err.println(format);
	}

	public static Map<String, Object> getParams() {
		Map<String, Object> p = Maps.newHashMap();
		p.put("userId", 423229);
		p.put("anchorId", 423229);
		p.put("roomId", 12123);

		p.put("machine_MAC", "28f36649c76a");
		p.put("platform_id", 423229);
		p.put("platform_userid", 423229);
		p.put("platform_id", 1487571676);

		p.put("commend", 1);

		return p;
	}

	public static String setParam() {
		Map<String, Object> p = getParams();

		String f = "";
		StringBuilder sb = new StringBuilder(url);
		if (url.indexOf("?") < 0) {
			sb.append("?");
		} else {
			f = "&";
		}
		for (String key : p.keySet()) {
			Object v = p.get(key);
			sb.append(f).append(key).append("=").append(v);
			f = "&";
		}
		return sb.toString();
	}

}
