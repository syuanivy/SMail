package shuai.webmail.entities;

public class User {
	String name;
	int age;
    String password;
	public User(String name, int age, String password) {
		this.name = name;
		this.age = age;
        this.password = password;
	}
	public String getName() { return name; }
	public int getAge() { return age; }
    public String getPassword(){return password;}
	public String toString() { return name+":"+age; }
}
