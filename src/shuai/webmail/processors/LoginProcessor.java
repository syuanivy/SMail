package shuai.webmail.processors;

import shuai.webmail.entities.Account;
import shuai.webmail.entities.User;
import shuai.webmail.managers.AccountManager;
import shuai.webmail.managers.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginProcessor extends PostProcessor {

    public LoginProcessor(HttpServletRequest request,
                          HttpServletResponse response){
        super(request, response);
    }

    @Override
    public void processPost() throws SQLException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Boolean success = UserManager.isPWCorrect(username, password);


        if (success) {
            User user = UserManager.checkUserInfo(username);
            Account account = AccountManager.getUserAccount(username);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("account", account);
            response.sendRedirect("/inbox");
        } else {
            request.setAttribute("error", "Unknown user, please try again");
            response.sendRedirect("/");
        }
    }

}
