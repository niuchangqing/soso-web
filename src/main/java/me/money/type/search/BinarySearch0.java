package me.money.type.search;

import me.money.type.log.Log;

/**
 * @author Niucqing
 * @email niucqing@gmail.com
 * 二分查找
 */
public class BinarySearch0 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		int index = binarySearch1(a, 0, a.length-1, 11);
		Log.log("find index is", index);
	}

	/**
	 * while循环算法
	 * @param a
	 * @param from
	 * @param to
	 * @param key
	 * @return
	 */
	public static int binarySearch0(int[] a, int from, int to, int key) {
		int low = from;
		int high = to - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			Log.log("mid", mid);
			if (a[mid] < key) {
				low = mid + 1;
			} else if (a[mid] > key) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	/**
	 * 递归算法
	 * @param a
	 * @param from
	 * @param to
	 * @param key
	 * @return
	 */
	public static int binarySearch1(int[] a, int from, int to, int key) {
		int mid = (from + to) / 2;
		Log.log("mid", mid);
		if (a[mid] < key) {
			from = mid + 1;
		} else if (a[mid] > key) {
			to = mid - 1;
		} else {
			return mid;
		}
		return binarySearch1(a, from, to, key);
	}

}
