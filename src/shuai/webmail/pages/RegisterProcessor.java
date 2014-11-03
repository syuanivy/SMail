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
        String password1 = request.getParameter("password");
        String password2 = request.getParameter("retype");

        Boolean exist = UserManager.isUser(username);

        if (!exist) {
            if(password1.equals (password2)){
                boolean success = UserManager.addUser(username, password1);
                if(success){
                    request.getSession().setAttribute("user", username);
                    response.sendRedirect("/welcome");
                }else{
                    request.setAttribute("error", "registration failed due to internal error"); // may not be necessary at all
                    response.sendRedirect("/register");
                }
            }else{
                request.setAttribute("error", "Retyping has to be the same!");
                response.sendRedirect("/register");
            }

        } else {
            request.setAttribute("error", "User existing, please choose another username!");
            response.sendRedirect("/register");
        }
    }
}
