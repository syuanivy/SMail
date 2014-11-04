package shuai.webmail.pages;


import org.stringtemplate.v4.ST;
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




    public void verify() { }

    @Override
    public ST body() {

        String username = (String) request.getSession().getAttribute("user");

        if(username == null){
            try{
                response.sendRedirect("/");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        ST st = templates.getInstanceOf("inbox");
        st.add("user", username);
        return st;
    }

    @Override
    public ST getTitle() {
        return new ST("inbox page");
    }
}
