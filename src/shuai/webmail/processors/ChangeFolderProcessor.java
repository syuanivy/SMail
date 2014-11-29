package shuai.webmail.processors;

import shuai.webmail.entities.Account;
import shuai.webmail.entities.Outgoing;
import shuai.webmail.managers.EmailManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ivy on 11/27/14.
 */
public class ChangeFolderProcessor extends PostProcessor {
    public ChangeFolderProcessor(HttpServletRequest request,
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
        String id = request.getParameter("id");
        int labelBefore = Integer.parseInt(request.getParameter("labelBefore"));
        int labelAfter = Integer.parseInt(request.getParameter("labelAfter"));
        EmailManager.changeFolder(id, labelBefore, labelAfter);
        response.sendRedirect("/home?folder="+labelBefore);
    }
}
