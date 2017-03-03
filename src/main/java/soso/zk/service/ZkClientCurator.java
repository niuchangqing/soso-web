package soso.zk.service;

import java.io.UnsupportedEncodingException;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import soso.utils.TimePause;

public class ZkClientCurator {
	public static void main(String[] args) throws UnsupportedEncodingException, Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
		client.start();
		client.create().creatingParentsIfNeeded().forPath("/niucqing/t/url", "http://www.baidu.com".getBytes("utf-8"));
		System.out.println("created...");
		TimePause.pause(1000);
	}
}
