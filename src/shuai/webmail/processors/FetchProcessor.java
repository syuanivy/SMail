package shuai.webmail.processors;

import shuai.webmail.entities.Account;
import shuai.webmail.mail_services.POPService;
import shuai.webmail.mail_services.SMTPService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ivy on 11/10/14.
 */
public class FetchProcessor extends PostProcessor {
    public FetchProcessor(HttpServletRequest request,
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
        Account accountToShow = (Account) request.getSession().getAttribute("accountToShow");
        if(accountToShow == null){
            response.sendRedirect("/login");
            return;
        }
        POPService request = new POPService(accountToShow);
        try {
            request.retrieve();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/home");
    }

}
