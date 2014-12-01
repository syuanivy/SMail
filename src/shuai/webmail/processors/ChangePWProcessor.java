package shuai.webmail.processors;

import shuai.webmail.entities.Account;
import shuai.webmail.entities.User;
import shuai.webmail.managers.AccountManager;
import shuai.webmail.managers.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ivy on 11/28/14.
 */
public class ChangePWProcessor extends PostProcessor {
    public ChangePWProcessor(HttpServletRequest request,
                              HttpServletResponse response){
        super(request, response);
    }
    @Override
    public void verify() throws IOException {
        if(request.getSession().getAttribute("user")==null || request.getSession().getAttribute("accountToShow")==null){
            try{
                response.sendRedirect("/");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void processPost() throws SQLException, IOException{
        User user = (User) request.getSession().getAttribute("user");
        Account accountToShow = (Account) request.getSession().getAttribute("accountToShow");

        if(accountToShow == null){
            response.sendRedirect("/login");
            return;
        }

        String newPW = request.getParameter("password");
        String reType = request.getParameter("retype");
        if (newPW.equals(reType)) {
            UserManager.changePassword(user, newPW);
            response.sendRedirect("/home");
        } else{
            request.setAttribute("error", "Retype has to be the same, try input password again!");
            response.sendRedirect("/userinfo");
        }
    }

}
