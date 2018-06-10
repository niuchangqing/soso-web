package me.money.type.pa;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import me.money.type.object.pool.Pools;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Pa {

	private static Logger logger = LoggerFactory.getLogger(Pa.class);
	private static AtomicInteger times = new AtomicInteger(0);

	public static void main(String[] args) throws IOException {
//		List<String> ids = load();
		List<String> ids = FileUtils.readLines(new File("D://JIJIN/error.txt"),"utf-8");
		for (String string : ids) {
			File file = new File("D://JIJIN/"+string+".txt");
			if(file.exists()) {
				file.delete();
			}
		}
		start(ids);
	}

	public static void start(final List<String> ids) {
		for (String id : ids) {
			Pools.submit(new Runnable() {
				@Override
				public void run() {
					detail(id);
				}
			});
		}
	}

	public static List<String> load() {
		List<String> ids = new ArrayList<String>();
		try {
			String readFileToString = FileUtils.readFileToString(new File("D://jijin.txt"), "utf-8");
			JSONArray parseArray = JSON.parseArray(readFileToString);
			for (Object object : parseArray) {
				List<String> list = JSON.parseArray(object.toString(), String.class);
				System.out.println(list.get(0) + "--" + list.get(1));
				ids.add(list.get(0));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ids;
	}

	public static void detail(String id) {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		final String url = "http://api.fund.eastmoney.com/f10/lsjz?fundCode=%s&pageIndex=%s&pageSize=30&startDate=&endDate=&_=1528601899541";
		List<String> jsonList = new ArrayList<String>();
		try {
			int index = 0;
			while (true) {
				String localUrl = String.format(url, id, index);
				Request req = new Request.Builder().url(localUrl).header("User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
						.addHeader("Accept", "*/*").addHeader("DNT", "1").addHeader("Host", "api.fund.eastmoney.com")
						.addHeader("Referer", "http://fund.eastmoney.com/f10/jjjz_003407.html").build();
				Response res = client.newCall(req).execute();
				String resString = "";
				if (res.isSuccessful()) {
					resString = res.body().string();
				} else {
					throw new RuntimeException();
				}
				Model model = JSON.toJavaObject(JSON.parseObject(resString), Model.class);
				List<DetailModel> details = model.getData().getLSJZList();
				if (details.isEmpty()) {
					break;
				}
				for (DetailModel bean : details) {
					bean.setID(id);
					String json = JSON.toJSONString(bean);
					jsonList.add(json);
				}
				if (details.size() < 30) {
					break;
				}
				TimeUnit.MILLISECONDS.sleep(200);
				index++;
			}
			String fileName = "D://JIJIN/" + id + ".txt";
			File file = new File(fileName);
			FileUtils.writeLines(file, "utf-8", jsonList, true);
			System.out.println("id--" + id + "---" + times.incrementAndGet());

		} catch (Exception e) {
			logger.error("", e);
			try {
				FileUtils.write(new File("D://JIJIN/error.txt"), id + System.lineSeparator(), "utf-8", true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public static void totalIds() {
		OkHttpClient client = new OkHttpClient().newBuilder().readTimeout(2, TimeUnit.SECONDS)
				.connectTimeout(2, TimeUnit.SECONDS).build();
		final String url = "http://fund.eastmoney.com/Data/Fund_JJJZ_Data.aspx?t=1&lx=1&letter=&gsid=&text=&sort=zdf,desc&page=1,9999&feature=|&dt=1528599028532&atfc=&onlySale=0";
		try {
			Request req = new Request.Builder().url(url).header("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
					.addHeader("Accept", "*/*").addHeader("DNT", "1").addHeader("Host", "fund.eastmoney.com").build();
			Response res = client.newCall(req).execute();
			if (res.isSuccessful()) {
				String resString = res.body().string();
				FileUtils.write(new File("D://jijin.txt"), resString, "utf-8");
			}
		} catch (IOException e) {
			logger.error("", e);
		}
	}

}
