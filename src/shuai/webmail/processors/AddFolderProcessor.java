package shuai.webmail.processors;

import shuai.webmail.entities.Account;
import shuai.webmail.entities.Outgoing;
import shuai.webmail.entities.User;
import shuai.webmail.mail_services.SMTPService;
import shuai.webmail.managers.AccountManager;
import shuai.webmail.managers.EmailManager;
import shuai.webmail.managers.MyFolder;
import shuai.webmail.managers.UserManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ivy on 11/28/14.
 */
public class AddFolderProcessor extends PostProcessor {

    public AddFolderProcessor(HttpServletRequest request,
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
        Account account = (Account) request.getSession().getAttribute("account");
        if(account == null){
            response.sendRedirect("/login");
            return;
        }

        String foldername = request.getParameter("foldername");
        account.folders.newFolder(foldername);
        AccountManager.updateFolders(account);
        response.sendRedirect("/home");
    }

}
