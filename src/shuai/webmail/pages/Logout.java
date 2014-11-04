package shuai.webmail.pages;

import shuai.webmail.managers.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by syuan6 on 11/3/14.
 */
public class Logout extends Page {
    public Logout(HttpServletRequest request,
                  HttpServletResponse response){
        super(request, response);
    }

    @Override
    public void processPost() throws SQLException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("/");
    }

}
