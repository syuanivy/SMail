package shuai.webmail.pages;

import shuai.webmail.managers.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ivy on 11/2/14.
 */
public class RegisterProcessor extends PostProcessor{
    public RegisterProcessor(HttpServletRequest request,
                          HttpServletResponse response){
        super(request, response);
    }

    @Override
    public void processPost() throws SQLException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Boolean exist = UserManager.isUser(username);

        if (!exist) {
            request.getSession().setAttribute("user", username);
            //insert into database;
            response.sendRedirect("/welcome");
        } else {
            request.setAttribute("error", "User existing, please choose another username!");
            response.sendRedirect("/registerprocessor");
        }
    }
}
