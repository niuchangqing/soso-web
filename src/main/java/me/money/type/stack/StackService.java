package me.money.type.stack;

import java.util.Stack;

import me.money.type.log.Log;

public class StackService {

	private static final Stack<Integer> stack = new Stack<Integer>();

	public static void add(Integer value) {
		stack.push(value);
	}

	public static void pop() {
		while (!stack.isEmpty()) {
			Log.log(stack.pop());
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			add(i);
		}
		pop();
	}

}
