package shuai.webmail.pages;

import org.stringtemplate.v4.ST;
import shuai.webmail.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ivy on 10/26/14.
 */
public class WelcomePage extends Page {
    public WelcomePage(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }
    /*
    static User[] users = new User[] {
            new User("Boris", "123"),
            new User("Natasha", "abc"),
            new User("Jorge", "xyz"),
            new User("Vladimir","456")
    };
    */
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
        ST st = templates.getInstanceOf("welcome");
        st.add("name", username);
        return st;
    }

    @Override
    public ST getTitle() {
        return new ST("welcome page");
    }
}
