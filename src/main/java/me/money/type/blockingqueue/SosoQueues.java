package me.money.type.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import me.money.type.log.Log;

public class SosoQueues {
	public static final BlockingQueue<String> linkedQueue = new LinkedBlockingQueue<String>();

	public static void main(String[] args) {
	}
	
	public static void put(String msg) {
		try {
			linkedQueue.put(msg);
		} catch (InterruptedException e) {
			Log.log(e);
		}
	}

	public static String take() {
		try {
			return linkedQueue.take();
		} catch (InterruptedException e) {
			Log.log(e);
		}
		return null;
	}
}
