package me.money.type.search;

import me.money.type.log.Log;

/**
 * @author Niucqing
 * @email niucqing@gmail.com
 */
public class Triangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int triangle = triangle1(6);
		Log.log(triangle);
	}

	/**
	 * 1,3,6,10,15,21....
	 * 求第n个
	 * @param index
	 * @return
	 */
	public static int triangle(int n) {
		if (n == 1) {
			return 1;
		}
		//5+4+3+2+1
		return n + triangle(n - 1);
	}

	public static int triangle1(int n) {
		if (n == 1) {
			return 1;
		}
		int x = 2;
		int sum = 1;
		for (int j = 1; j < n; j++) {
			sum += x;
			x++;
		}
		return sum;
	}

}
