package me.money.type.callback;
/**
* @author Niucqing
* @email niucqing@gmail.com
* @version 创建时间：2018年3月21日 下午6:26:08
* 类说明
*/
public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Teacher t = new Teacher();
		Mother m = new Mother();
		StudentA a = new StudentA();
		a.doSomething(t,m);
	}

}
