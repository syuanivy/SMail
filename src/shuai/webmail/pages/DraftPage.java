package shuai.webmail.pages;

import org.stringtemplate.v4.ST;
import shuai.webmail.entities.Account;
import shuai.webmail.entities.Email;
import shuai.webmail.entities.User;
import shuai.webmail.managers.EmailManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ivy on 11/26/14.
 */
public class DraftPage extends Page {

    public DraftPage(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }




    public void verify() {
        if(request.getSession().getAttribute("user")==null || request.getSession().getAttribute("account")==null){
            try{
                response.sendRedirect("/");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public ST body() {
        User user = (User) request.getSession().getAttribute("user");
        Account account = (Account) request.getSession().getAttribute("account");
        ST home = templates.getInstanceOf("home");
        ST center = templates.getInstanceOf("home_center");
        ST table = templates.getInstanceOf("sent_tabledisplay");
        ArrayList<Email> sentMails = new ArrayList<Email>();
        try{
            sentMails = EmailManager.mailList(account, 2);
        }catch(SQLException e){
            e.printStackTrace();
        }


        if(user != null & account != null){
            home.add("user", user.getName());
            home.add("account", account.getEmailAddress());
            center.add("account", account.getEmailAddress());
            table.add("sent", sentMails);
            center.add("table", table);
            home.add("center",center);
            return home;
        }else{
            return null;
        }
    }

    @Override
    public ST getTitle() {
        return new ST("sent_page");
    }


}
