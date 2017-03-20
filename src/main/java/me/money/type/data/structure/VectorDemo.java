package me.money.type.data.structure;

import java.util.Vector;

public class VectorDemo {

	public static void main(String[] args) {
		Vector<Integer> v = new Vector<Integer>();
		for (int i = 0; i < 10; i++) {
			v.add(i);
		}

		v.forEach(e -> System.out.println(e));

		v.forEach(e -> {
			if (e > 5)
				System.out.println(e);
		});
	}
}
