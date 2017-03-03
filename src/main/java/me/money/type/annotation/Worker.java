package me.money.type.annotation;

public class Worker {

	@UserName("niucqing")
	private String name;

	@UserSex("男")
	private String sex;

	@WorkAddress(address = "北京市海淀区苏州街", floor = 10)
	private String workinfo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getWorkinfo() {
		return workinfo;
	}

	public void setWorkinfo(String workinfo) {
		this.workinfo = workinfo;
	}

}
