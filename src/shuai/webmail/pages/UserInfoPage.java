package shuai.webmail.pages;

import org.stringtemplate.v4.ST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ivy on 10/26/14.
 */
public class UserInfoPage extends Page {
    public UserInfoPage(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void verify() { }

    @Override
    public ST body() {
        return templates.getInstanceOf("userinfo");
    }

    @Override
    public ST getTitle() {
        return new ST("userinfo page");
    }
}
