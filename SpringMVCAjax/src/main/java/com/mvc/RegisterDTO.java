package com.mvc;

public class RegisterDTO {
	private String memberId;
	private String passwd;
	private String name;
	private String gender;
	private int age;

	public RegisterDTO() {
	}

	public RegisterDTO(String memberId, String passwd, String name, String gender, int age) {
		this.memberId = memberId;
		this.passwd = passwd;
		this.name = name;
		this.gender = gender;
		this.age = age;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "RegisterDTO [memberId=" + memberId + ", passwd=" + passwd + ", name=" + name + ", gender=" + gender
				+ ", age=" + age + "]";
	}

}
