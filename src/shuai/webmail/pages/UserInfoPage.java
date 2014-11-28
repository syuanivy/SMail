package shuai.webmail.pages;

import org.stringtemplate.v4.ST;
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

    public void verify() {
        if(request.getSession().getAttribute("user")==null){
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
        ST st = templates.getInstanceOf("userinfo");
        if(user != null){
            st.add("user", user.getName());
            return st;
        }else{
            return null;
        }
    }

    @Override
    public ST getTitle() {
        return new ST("userinfo page");
    }
}
