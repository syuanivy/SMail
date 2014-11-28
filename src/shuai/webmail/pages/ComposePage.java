package shuai.webmail.pages;

import org.stringtemplate.v4.ST;
import shuai.webmail.entities.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ivy on 10/26/14.
 */
public class ComposePage extends Page{
    public ComposePage(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void verify() {
        if(request.getSession().getAttribute("account") == null){
            try{
                response.sendRedirect("/");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public ST body() {

        Account account = (Account) request.getSession().getAttribute("account");
        String sender = account.getEmailAddress();
        String user = account.getLocalUser();
        ST st = templates.getInstanceOf("compose");
        st.add("sender", sender);
        st.add("user", user);
        return st;
    }

    @Override
    public ST getTitle() {
        return new ST("compose page");
    }
}
