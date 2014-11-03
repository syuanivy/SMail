package shuai.webmail.entities;

public class User {
    public int id = -1;
	public String name;
    public String password;

    public User() {

    }
	public User(String name, String password) {
		this.name = name;
        this.password = password;
	}
    public int getId() { return id; }
	public String getName() { return name; }
    public String getPassword(){return password;}

}
