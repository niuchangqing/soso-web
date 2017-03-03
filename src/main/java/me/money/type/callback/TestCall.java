package me.money.type.callback;

public class TestCall{
	public static void main(String[] args) {

		Employer e = new Employer(new Boss());
		e.doSome();
		
	}
}
