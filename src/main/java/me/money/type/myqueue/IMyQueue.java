package me.money.type.myqueue;

public interface IMyQueue<T> {
	public void add(T t);

	public T pop();
}
