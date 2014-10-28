package shuai.webmail.pages;


import org.stringtemplate.v4.ST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InboxPage extends Page{
    public InboxPage(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void verify() { }

    @Override
    public ST body() {
        return templates.getInstanceOf("inbox");
    }

    @Override
    public ST getTitle() {
        return new ST("inbox page");
    }
}
