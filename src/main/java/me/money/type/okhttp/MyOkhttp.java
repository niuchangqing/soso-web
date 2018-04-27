package me.money.type.okhttp;

import java.io.IOException;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.jsoniter.Any;
import com.jsoniter.JsonIterator;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyOkhttp {
	private static final OkHttpClient client = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	public static void main(String[] args) throws IOException {
		String url = "http://hot.active.qxiu.com/cgi/af/online?userId=111";
		call(url);
		// post(url);
	}

	public static void call(String url) throws IOException {
		Request req = new Request.Builder().url(url).build();
		Response res = client.newCall(req).execute();
		if (res.isSuccessful()) {
			String rs = res.body().string();
			System.out.println(rs);
			JsonIterator iter = JsonIterator.parse(rs);
			Any any = iter.readAny();
			Map<String, Object> result = any.getMap("result");
			long count = Math.round((Double) result.get("online"));
			System.out.println(count);
			System.out.println(res.message());
			System.out.println(res.receivedResponseAtMillis());
		}
	}

	public static void post(String url) throws IOException {
		Map<String, Integer> param = Maps.newHashMap();
		param.put("userId", 111);
		String json = new Gson().toJson(param);
		RequestBody body = RequestBody.create(JSON, json);
		Request req = new Request.Builder().url(url).post(body).build();
		Response res = client.newCall(req).execute();
		if (res.isSuccessful()) {
			System.out.println(res.body().string());
		}
	}
}
