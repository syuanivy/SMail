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
            if(request.getSession().getAttribute("user")==null || request.getSession().getAttribute("accountToShow")==null){
                try{
                    response.sendRedirect("/");
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        @Override
        public void processPost() throws SQLException, IOException {
            Account accountToShow = (Account) request.getSession().getAttribute("accountToShow");

            ArrayList<Email> mails_in_trash = EmailManager.mailList(accountToShow,4);
            for(Email trash_mail : mails_in_trash){
                EmailManager.changeFolder(trash_mail.id, 5);
            }
            response.sendRedirect("/home");
        }
}
