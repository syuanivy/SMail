package shuai.webmail.processors;

import com.sun.xml.internal.bind.v2.TODO;
import shuai.webmail.entities.User;
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
        String retype = request.getParameter("retype");

        //TODO: AJAX client side: check existing user and password1 == password2;
        Boolean exist = UserManager.isUser(username);
        Boolean match = password.equals(retype);

        if (!exist & match) {
            User newuser = UserManager.addUser(username, password);
            request.getSession().setAttribute("user", newuser);
            response.sendRedirect("/welcome");
        } else{
            if(exist) request.setAttribute("error", "User existing, please choose another username!");
            if(!match) request.setAttribute("error", "Retype has to be the same, try input password again!");
            response.sendRedirect("/register");
        }
    }
}
