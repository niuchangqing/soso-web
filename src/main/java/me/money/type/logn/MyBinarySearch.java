package me.money.type.logn;

import java.util.Arrays;

public class MyBinarySearch {
	public static void main(String[] args) {
		int arr[] = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int index = search(arr, 0, arr.length, 8);
		System.out.println(index);
		
		Arrays.binarySearch(arr, 1);
	}

	public static int search(int[] searcharry, int start, int end, int value) {
		int mid = (start + end) / 2;
		if (start > end) {
			return -1;
		}
		int midvalue = searcharry[mid];

		if (value == midvalue) {
			return mid;
		} else if (value > midvalue) {
			return search(searcharry, mid + 1, end, value);
		} else if (value < midvalue) {
			return search(searcharry, start, mid - 1, value);
		}

		return -1;
	}
}
