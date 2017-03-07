package me.money.type.callback;

import me.money.type.log.Log;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestCallBack().compute(10, new ComputeCallBack() {
			
			@Override
			public void execute() {
				Log.log("over ...");
				
			}
		});
	}

}
