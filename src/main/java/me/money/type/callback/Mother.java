package me.money.type.callback;

/**
* @author Niucqing
* @email niucqing@gmail.com
* @version 创建时间：2018年3月21日 下午6:27:31
* 类说明
*/
public class Mother implements CallBack {
	@Override
	public void complete(String msg) {
		System.out.println("妈妈收到回复:" + msg);
	}
}
