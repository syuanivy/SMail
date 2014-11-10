package shuai.webmail.processors;

import shuai.webmail.entities.Account;
import shuai.webmail.entities.Outgoing;
import shuai.webmail.mail_services.SMTPService;
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
       Account account = (Account) request.getSession().getAttribute("account");
       String[] outgoingFields = {account.getEmailAddress(),request.getParameter("recipient"),
                request.getParameter("subject"),request.getParameter("body")};

        Outgoing email = new Outgoing(outgoingFields[0], outgoingFields[1],outgoingFields[2],outgoingFields[3]);
        SMTPService request = new SMTPService(email, account);
        request.send();

       //TODO: save outgoing emails in db.
/*     addOutgoing(Outgoing email);
       if (successful) email.sent= 1; // go to sent
       else email.sent = 0; // go to draft
*/

        response.sendRedirect("/home");
        }



}
