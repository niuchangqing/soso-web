package me.money.type.callback;

import soso.utils.TimePause;

/**
* @author Niucqing
* @email niucqing@gmail.com
* @version 创建时间：2018年3月21日 下午6:14:48
* 类说明
*/
public class StudentA {
	
	public void doSomething(CallBack... cbs) {
		System.out.println("studentA do working...");
		TimePause.pause(2);
		for (CallBack cb : cbs) {
			cb.complete("StudentA woking done");
		}
	}
}
