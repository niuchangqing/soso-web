package me.money.type.callback;

import me.money.type.log.Log;

public class TestCallBack {
	public void compute(int a, ComputeCallBack callback) {
		int sum = 0;
		for (int i = 0; i < 100; i++) {
			sum += i;
		}
		Log.log(sum);
		callback.execute();
	}
}
