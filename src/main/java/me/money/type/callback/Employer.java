package me.money.type.callback;

import me.money.type.log.Log;

public class Employer {
	public void doSome(int c, BossCallBack callback) {
		while (c < 10) {
			c++;
			System.err.println("do some " + c);
		}
		callback.execute();
	}
	
	public static void main(String[] args) {
		new Employer().doSome(1, new BossCallBack() {
			
			@Override
			public void execute() {
				// TODO Auto-generated method stub
				Log.log("boss i worked");
			}
		});
	}
}
