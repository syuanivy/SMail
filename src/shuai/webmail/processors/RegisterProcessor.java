package shuai.webmail.processors;

import com.sun.xml.internal.bind.v2.TODO;
import shuai.webmail.entities.Account;
import shuai.webmail.entities.User;
import shuai.webmail.managers.AccountManager;
import shuai.webmail.managers.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

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
        //All fields are required, which is enforced by the UI.
        String[] userFields = {request.getParameter("username"),request.getParameter("password"),request.getParameter("retype")};
        String[] accountFields = {request.getParameter("email"),request.getParameter("smtp"),
                request.getParameter("smtpPort"),request.getParameter("pop"),request.getParameter("popPort"),
                request.getParameter("ssl"),request.getParameter("emailUsername"),request.getParameter("emailPassword"),
                userFields[0]};


        //TODO: AJAX client side: check existing user and password1 == password2;
        User checkuser = UserManager.checkUserInfo(userFields[0]);
        Boolean exist = (checkuser != null);
        Boolean match = userFields[1].equals(userFields[2]);

        //TODO: AJAX client side: check all fields are filled;

        if (!exist & match) {
            User newuser = UserManager.addUser(userFields);
            Account newaccount = AccountManager.addAccount(accountFields);
            request.getSession().setAttribute("user", newuser);
            request.getSession().setAttribute("account", newaccount);
            response.sendRedirect("/welcome");
        } else{
            if(exist) request.setAttribute("error", "User existing, please choose another username!");
            if(!match) request.setAttribute("error", "Retype has to be the same, try input password again!");
            response.sendRedirect("/register");
        }
    }



}
