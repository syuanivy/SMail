package shuai.webmail.pages;

import org.stringtemplate.v4.ST;
import shuai.webmail.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ivy on 10/26/14.
 */
public class WelcomePage extends Page {
    public WelcomePage(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    /** Our simulated database */
    static User[] users = new User[] {
            new User("Boris", 39, "123"),
            new User("Natasha", 31,"abc"),
            new User("Jorge", 25,"xyz"),
            new User("Vladimir", 28,"456")
    };
    public void verify() { }

    @Override
    public ST body() {
        ST st = templates.getInstanceOf("welcome");
        st.add("name", users[0].getName());
        return st;
    }

    @Override
    public ST getTitle() {
        return new ST("welcome page");
    }
}
