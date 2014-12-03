package shuai.webmail.pages;

import org.stringtemplate.v4.ST;
import shuai.webmail.entities.Account;
import shuai.webmail.entities.Email;
import shuai.webmail.entities.Incoming;
import shuai.webmail.entities.User;
import shuai.webmail.managers.EmailManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ivy on 11/23/14.
 */
public class MessageDisplayPage extends Page {
    public MessageDisplayPage(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }




    public void verify() {
    }

    @Override
    public ST body() {
        if(request.getSession().getAttribute("accountToShow" )== null) {
            try{
                response.sendRedirect("/");
                return null;
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        User user = (User) request.getSession().getAttribute("user");
        Account primary_account= (Account) request.getSession().getAttribute("primary_account");
        Account second_account = (Account) request.getSession().getAttribute("second_account");
        Account account = (Account) request.getSession().getAttribute("accountToShow");

        String emailID = request.getParameter("id");
        String label = request.getParameter("label");
        Email email = new Email();
        try{
            email = EmailManager.findEmail(emailID,Integer.parseInt(label));
            if(email.label==0) EmailManager.changeFolder(email.id, 3);
        }catch(SQLException e){
            e.printStackTrace();
        }
        ST frame = templates.getInstanceOf("home_display_reply");
        ST navbar = templates.getInstanceOf("home_navbar");
        ST leftbar = new ST("");
        try {
            LeftSideBar leftSideBar = LeftSideBar.generateBar(account);
            leftbar = leftSideBar.leftbar;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ST message = templates.getInstanceOf("display_reply");

        if(user != null && account != null){
            frame.add("user", user);
            frame.add("account", account.getEmailAddress());
            navbar.add("user", user);
            navbar.add("account", primary_account);
            navbar.add("second_account", second_account);
            frame.add("navbar",navbar);
            frame.add("leftbar",leftbar);
            message.add("email", email);
            message.add("account", account.getEmailAddress());
            frame.add("display_reply", message);
            return frame;
        }else{
            return null;
        }
    }

    @Override
    public ST getTitle() {
        return new ST("message_display");
    }
}
