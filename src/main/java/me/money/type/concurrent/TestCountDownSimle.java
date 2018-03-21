package me.money.type.concurrent;
/**
* @author Niucqing
* @email niucqing@gmail.com
* @version 创建时间：2018年3月21日 下午6:40:48
* 类说明
*/

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestCountDownSimle {

	static CountDownLatch cd = new CountDownLatch(10);
	static ExecutorService es = Executors.newCachedThreadPool();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			es.execute(new Runnable() {

				@Override
				public void run() {
					int nextInt = r.nextInt(15);
					try {
						System.out.println("执行" + nextInt + " 秒");
						TimeUnit.SECONDS.sleep(nextInt);
					} catch (InterruptedException e) {
						System.out.println("excetion");
					}
					cd.countDown();
				}
			});
		}

		System.out.println("所有线程已经开始执行");
		cd.await();
		System.out.println("全部执行完毕");
	}

}
