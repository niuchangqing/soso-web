package me.money.type.test;

public class MyOver extends Base {
	public MyOver() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		int i = 10;
		i+=10;
		
	}

	public static int num(int i)

	{

		if (i < 0)
			return 0;

		else if (i > 0 && i <= 2)
			return 1;

		else
			return num(i - 1) + num(i - 2);

	}
}
