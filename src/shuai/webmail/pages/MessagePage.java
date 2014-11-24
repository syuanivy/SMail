package shuai.webmail.pages;

import org.stringtemplate.v4.ST;
import shuai.webmail.entities.Account;
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
public class MessagePage extends Page {
    public MessagePage(HttpServletRequest request, HttpServletResponse response) {
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

/*    @Override
    public ST script() {return new ST("<script></script>");}

   */ @Override
    public ST body() {
        User user = (User) request.getSession().getAttribute("user");
        Account account = (Account) request.getSession().getAttribute("account");
        String emailID = request.getParameter("id");
        Incoming email = new Incoming();
        try{
            email = EmailManager.findEmail(emailID,2);
        }catch(SQLException e){
            e.printStackTrace();
        }
        ST frame = templates.getInstanceOf("home_display_reply");
        ST message = templates.getInstanceOf("display_reply");
//        Incoming email = new Incoming("fakefake", "fakesender@gmail.com", "fakerecipient@gmail.com", "fakesubject", "fakebody");


        if(user != null & account != null){
            frame.add("user", user.getName());
            frame.add("account", account.getEmailAddress());
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
