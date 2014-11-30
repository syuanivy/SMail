package shuai.webmail.processors;

import shuai.webmail.entities.Account;
import shuai.webmail.entities.Email;
import shuai.webmail.entities.User;
import shuai.webmail.managers.AccountManager;
import shuai.webmail.managers.EmailManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ivy on 11/30/14.
 */
public class EmptyTrashProcessor extends PostProcessor{
        public EmptyTrashProcessor(HttpServletRequest request,
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
        public void processPost() throws SQLException, IOException {
            Account account;
            if(request.getSession().getAttribute("accountToShow") != null) account = (Account) request.getSession().getAttribute("accountToShow");
            else account = (Account) request.getSession().getAttribute("account");
            ArrayList<Email> mails_in_trash = EmailManager.mailList(account,4);
            for(Email trash_mail : mails_in_trash){
                EmailManager.changeFolder(trash_mail.id, 5);
            }
            response.sendRedirect("/home");
        }
}
