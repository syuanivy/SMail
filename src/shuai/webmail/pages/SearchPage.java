package shuai.webmail.pages;

import org.stringtemplate.v4.ST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ivy on 10/26/14.
 */
public class SearchPage extends Page{
    public SearchPage(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void verify() { }

    @Override
    public ST body() {
        return templates.getInstanceOf("search");
    }

    @Override
    public ST getTitle() {
        return new ST("search page");
    }
}
