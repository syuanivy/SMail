package shuai.webmail.processors;

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

/**
 * Created by ivy on 11/29/14.
 */
public class AddAccountProcessor extends PostProcessor{
    public AddAccountProcessor(HttpServletRequest request,
                          HttpServletResponse response){
        super(request, response);
    }
    @Override
    public void verify() throws IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        if(account == null){
            response.sendRedirect("/login");
            return;
        }
    }
    @Override
    public void processPost() throws SQLException, IOException{
        User user = (User) request.getSession().getAttribute("user");
        Account account = (Account) request.getSession().getAttribute("account");
        if(account == null){
            response.sendRedirect("/login");
            return;
        }
        //second account already exists, do not support add more accounts for now
        if(request.getSession().getAttribute("second_account") != null) response.sendRedirect("/userinfo");

        String[] accountFields = {request.getParameter("email"),request.getParameter("smtp"),
                request.getParameter("smtpPort"),request.getParameter("pop"),request.getParameter("popPort"),
                request.getParameter("ssl"),request.getParameter("emailUser"),request.getParameter("emailPassword"),
               user.getName() };

        //See if already exists in db
        ResultSet record = AccountManager.findAccount(accountFields[0]);
        if(record.next()) response.sendRedirect("/userinfo");


        Account newaccount = AccountManager.addAccount(accountFields);
        request.getSession().setAttribute("second_account", newaccount);
        request.getSession().setAttribute("accountToShow" , newaccount);
        response.sendRedirect("/userinfo");




    }

}
