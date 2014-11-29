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
import java.util.ArrayList;

public class LoginProcessor extends PostProcessor {

    public LoginProcessor(HttpServletRequest request,
                          HttpServletResponse response){
        super(request, response);
    }
    @Override
    public void verify(){}

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
            ArrayList<Account> accounts = AccountManager.getUserAccount(username);
            if(accounts.size()==0){
                request.setAttribute("error", "No associated account, try again!");
                response.sendRedirect("/");
            }else{
                Account account = accounts.get(0);
                request.getSession().setAttribute("user", checkuser);
                request.getSession().setAttribute("account", account);
                if(accounts.size()>1) request.getSession().setAttribute("second_account", accounts.get(1));
                response.sendRedirect("/inbox");
            }
        }else{
            request.setAttribute("error", "Wrong password, try again!");
            response.sendRedirect("/");
        }
    }

}
