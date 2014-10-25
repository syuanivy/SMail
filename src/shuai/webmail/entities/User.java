package shuai.webmail.entities;

public class User {
	String name;
	int age;
	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String getName() { return name; }
	public int getAge() { return age; }
	public String toString() { return name+":"+age; }
}
