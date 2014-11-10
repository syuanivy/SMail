package shuai.webmail.pages;


import org.stringtemplate.v4.ST;
import shuai.webmail.entities.Account;
import shuai.webmail.entities.User;
import shuai.webmail.managers.UserManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class InboxPage extends Page{
    public InboxPage(HttpServletRequest request, HttpServletResponse response) {
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
        ST st = templates.getInstanceOf("home");
        if(user != null & account != null){
            st.add("user", user.getName());
            st.add("account", account.getEmailAddress());
            return st;
        }else{
            return null;
        }
    }

    @Override
    public ST getTitle() {
        return new ST("home page");
    }
}
