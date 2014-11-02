package shuai.webmail.entities;

public class User {
    int id = -1;
	String name;
    String password;

	public User(String name, String password) {
		this.name = name;
        this.password = password;
	}
    public int getId() { return id; }
	public String getName() { return name; }
    public String getPassword(){return password;}

}
