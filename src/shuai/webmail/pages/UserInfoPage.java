package shuai.webmail.pages;

import org.stringtemplate.v4.ST;

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
        if(request.getSession() == null){
            try{
                response.sendRedirect("/");
            }catch(IOException e){
                e.printStackTrace();
            }
        }else if(request.getSession().getAttribute("user")==null){
            try{
                response.sendRedirect("/");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public ST body() {
        String username = (String) request.getSession().getAttribute("user");
        ST st = templates.getInstanceOf("userinfo");
        st.add("user", username);
        return st;

    }

    @Override
    public ST getTitle() {
        return new ST("userinfo page");
    }
}
