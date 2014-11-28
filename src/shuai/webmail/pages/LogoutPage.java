package shuai.webmail.pages;

import org.stringtemplate.v4.ST;
import shuai.webmail.managers.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LogoutPage extends Page {
    public LogoutPage(HttpServletRequest request,
                      HttpServletResponse response){
        super(request, response);
    }

    public void verify() {}

    @Override
    public ST body() {
        request.getSession().invalidate(); // invalidate the session and return the login page
        return templates.getInstanceOf("login");
    }

    @Override
    public ST getTitle() {
        return new ST("login page");
    }


}
