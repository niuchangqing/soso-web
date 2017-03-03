package me.money.type.model;

public class User extends Person {
	private int age;
	private String name;

	public User() {
		this(10);
	}

	public User(int age) {
		System.out.println("user 构造方法:" + age);
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

	{
		System.out.println("user init");

	}

	static {
		System.out.println("user static init");
	}
}
