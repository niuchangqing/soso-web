package me.money.type.callback;

/**
* @author Niucqing
* @email niucqing@gmail.com
* @version 创建时间：2018年3月21日 下午6:15:03
* 类说明
*/
public class Teacher implements CallBack {
	@Override
	public void complete(String msg) {
		System.out.println("老师收到回复：" + msg);

	}
}
