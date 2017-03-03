package me.money.type.model;

public class Person {
	private int age;
	private String name;

	protected String getPerson() {
		return "i am person";
	}

	public Person() {
		System.out.println("Person 构造方法");
	}

	static {
		System.out.println("Person 静态块");
	}
	{
		System.out.println("Person init{}块");
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
