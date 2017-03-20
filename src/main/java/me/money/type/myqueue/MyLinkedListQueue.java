package me.money.type.myqueue;

import java.util.LinkedList;

public class MyLinkedListQueue<T> implements IMyQueue<T> {

	private LinkedList<T> list = new LinkedList<T>();

	@Override
	public void add(T t) {
		list.addFirst(t);
	}

	@Override
	public T pop() {
		return list.pollLast();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public static void main(String[] args) {
		MyLinkedListQueue<String> q = new MyLinkedListQueue<String>();

		for (int i = 0; i < 10; i++) {
			q.add(i + "");
		}

		while (!q.isEmpty()) {
			System.out.println(q.pop());
		}
	}

}
