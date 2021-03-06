package shuai.webmail.pages;

import org.stringtemplate.v4.ST;
import shuai.webmail.entities.User;
import shuai.webmail.managers.UserManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginPage extends Page {
    public LoginPage(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void verify() {}

    @Override
    public ST body() {
        return templates.getInstanceOf("login");
    }

    @Override
    public ST getTitle() {
        return new ST("login page");
    }


}