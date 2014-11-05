package shuai.webmail.entities;

public class User {
    private int id = -1;
	private String name;
    private String password;

    public User() { }
	public User(String name, String password) {
		this.name = name;
        this.password = password;
	}


    public int getId() { return id; }
	public String getName() { return name; }
    public String getPassword(){return password;}


    public void setId(int userid) { this.id = userid; }
    public void setName(String username) { this.name = username; }
    public void setPassword(String pw){this.password = pw;}



}
