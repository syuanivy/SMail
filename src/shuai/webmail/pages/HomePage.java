package shuai.webmail.pages;

import org.stringtemplate.v4.ST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomePage extends Page {
	public HomePage(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	public void verify() { }

	@Override
	public ST body() {
		return templates.getInstanceOf("home");
	}

	@Override
	public ST getTitle() {
		return new ST("Home page");
	}
}
