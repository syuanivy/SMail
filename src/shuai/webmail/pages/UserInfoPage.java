package shuai.webmail.pages;

import org.stringtemplate.v4.ST;
import shuai.webmail.entities.Account;
import shuai.webmail.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ivy on 10/26/14.
 */
public class UserInfoPage extends Page {
    public UserInfoPage(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void verify() {}

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
        Account primary_account = (Account) request.getSession().getAttribute("primary_account");
        Account second_account = (Account) request.getSession().getAttribute("second_account");
        ST st = templates.getInstanceOf("userinfo");
        st.add("user", user);
        st.add("defaultAccount", primary_account);
        st.add("secondAccount", second_account);
        return st;

    }

    @Override
    public ST getTitle() {
        return new ST("userinfo page");
    }
}
