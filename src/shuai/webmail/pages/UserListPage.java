package shuai.webmail.pages;

import shuai.webmail.entities.User;
import org.stringtemplate.v4.ST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserListPage extends Page {
	public UserListPage(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	/** Our simulated database */
	static User[] users = new User[] {
		new User("Boris", 39),
		new User("Natasha", 31),
		new User("Jorge", 25),
		new User("Vladimir", 28)
	};

	@Override
	public ST body() {
		ST st = templates.getInstanceOf("userlist");
		st.add("users", users);
		return st;
	}

	@Override
	public ST getTitle() {
		return new ST("List of users");
	}
}
