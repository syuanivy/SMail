package shuai.webmail.processors;

import shuai.webmail.entities.Account;
import shuai.webmail.entities.Outgoing;
import shuai.webmail.mail_services.SMTPService;
import shuai.webmail.managers.AccountManager;
import shuai.webmail.managers.EmailManager;

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
       Account accountToShow = (Account) request.getSession().getAttribute("accountToShow");
       if(accountToShow == null){
           response.sendRedirect("/login");
           return;
       }

       String[] outgoingFields = {accountToShow.getEmailAddress(),request.getParameter("recipient"),
                request.getParameter("subject"),request.getParameter("body")};
        Outgoing email = new Outgoing(outgoingFields[0], outgoingFields[1],outgoingFields[2],outgoingFields[3]);
        SMTPService request = new SMTPService(email, accountToShow);
        try {
            request.send();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/home");
        }



}
