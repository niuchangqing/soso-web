package me.money.type.totota;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ToToTa {
	private static Logger logger = LoggerFactory.getLogger(ToToTa.class);
	private static final OkHttpClient client = new OkHttpClient();

	public static void main(String[] args) {
		List<String> phones = new ArrayList<String>();
		// phone.add("13120061957");
		phones.add("13833451966");// 牛解亮
		totota(phones);
	}

	public static void totota(List<String> phones) {
		ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);

		newScheduledThreadPool.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				maozhua(phones);
				liantong(phones);
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
				String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
				if (res.isSuccessful()) {
					String resString = res.body().string();
					logger.error(String.format("猫爪-totota %s-%s", phone, resString));
				} else {
					logger.error(String.format("猫爪-totota %s-%s", now, res.body().toString()));
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
				String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
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
}
