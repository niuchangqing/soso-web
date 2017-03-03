package me.money.type.okhttp;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserNickname {

	private static final String NICKURL = "http://cgi.qxiu.com/user/nickname?userids=[%s]";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		getUserNick("63921865", "370080");
	}
	

	public static void getUserNick(String... userId) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(10, TimeUnit.MILLISECONDS).build();

		Request req = new Request.Builder().url(String.format(NICKURL, StringUtils.join(userId, ","))).build();

		Response res = client.newCall(req).execute();
		if (res.isSuccessful()) {
			String json = res.body().string();
			System.out.println(json);
			JsonModel model = new Gson().fromJson(json, JsonModel.class);
			System.out.println(model.getRetcode());
			Map<String, Object> map = model.getResult();
			Map<String, String> nicks = (Map<String, String>) map.get("nicknames");
			for (String id : nicks.keySet()) {
				System.out.println(id + ":" + nicks.get(id));
			}
		}
	}

}
