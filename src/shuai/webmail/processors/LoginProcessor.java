package shuai.webmail.processors;

import org.eclipse.jetty.server.Authentication;
import shuai.webmail.entities.Account;
import shuai.webmail.entities.User;
import shuai.webmail.managers.AccountManager;
import shuai.webmail.managers.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
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
        User checkuser = UserManager.checkUserInfo(username);

        if(checkuser == null){
            response.sendRedirect("/"); //TODO: AJAX
            return;
        }

        boolean success = UserManager.isPWCorrect(password, checkuser.getPassword());
        if(success){
            Account account = AccountManager.getUserAccount(username);
            request.getSession().setAttribute("user", checkuser);
            request.getSession().setAttribute("account", account);
            response.sendRedirect("/inbox");
        }else{
            request.setAttribute("error", "Wrong password, try again!");
            response.sendRedirect("/");
        }
    }

}
