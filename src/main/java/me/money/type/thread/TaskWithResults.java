package me.money.type.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TaskWithResults implements Callable<String> {

	private int id;

	public TaskWithResults(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		TimeUnit.MICROSECONDS.sleep(500);
		return "result of taskwithresults " + id;
	}

}
