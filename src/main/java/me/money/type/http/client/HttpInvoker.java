package me.money.type.http.client;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Stopwatch;

import me.money.type.log.Log;

public class HttpInvoker {
	private static PoolingHttpClientConnectionManager cm = null;
	private static CloseableHttpClient httpClient = null;
	private static RequestConfig requestConfig = null;
	static {
		cm = new PoolingHttpClientConnectionManager();
		// Increase max total connection to 200
		cm.setMaxTotal(200);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(20);

		// Increase max connections for localhost:80 to 50
		HttpHost localhost = new HttpHost("locahost", 80);
		cm.setMaxPerRoute(new HttpRoute(localhost), 50);
		httpClient = HttpClients.custom().setConnectionManager(cm).build();
		Log.logSingleLine("cm.getTotalStats()", cm.getTotalStats());
	}

	public static void get(String url) throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse response = httpClient.execute(get);
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity);
		Log.log(result);
	}
	
	public static void main(String[] args) throws Exception, IOException {
		while(true){
			TimeUnit.SECONDS.sleep(1);
			Stopwatch s = Stopwatch.createStarted();
			get("http://datacenter.qxiu.com/cgi/anchor/micInfo?anchorIds=[423229]");
			long ss = s.elapsed(TimeUnit.MILLISECONDS);
			Log.logSingleLine(ss);
		}
	}
}
