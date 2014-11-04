package shuai.webmail.pages;

import org.stringtemplate.v4.ST;

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
        if(request.getSession() == null){
            try{
                response.sendRedirect("/");
            }catch(IOException e){
                e.printStackTrace();
            }
        }else if(request.getSession().getAttribute("user") == null){
            try{
                response.sendRedirect("/");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public ST body() {
        return templates.getInstanceOf("compose");
    }

    @Override
    public ST getTitle() {
        return new ST("compose page");
    }
}
