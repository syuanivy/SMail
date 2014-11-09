package shuai.webmail.processors;

import shuai.webmail.entities.Account;
import shuai.webmail.managers.AccountManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ivy on 11/8/14.
 */
public class ComposeProcessor extends PostProcessor {
    public ComposeProcessor(HttpServletRequest request,
                             HttpServletResponse response){
        super(request, response);
    }

    @Override
    public void processPost() throws SQLException, IOException {

      /*  Account account = (Account) request.getSession().getAttribute("account");
        String[] outgoingFields = {request.getParameter("sender"),request.getParameter("recipient"),
                request.getParameter("subject"),request.getParameter("body");





        Account newaccount = AccountManager.addAccount(accountFields);
        request.getSession().setAttribute("user", newuser);*//*
        request.getSession().setAttribute("account", newaccount);
        response.sendRedirect("/welcome");*/

        }



}
