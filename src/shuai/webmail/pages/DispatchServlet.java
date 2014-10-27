package shuai.webmail.pages;

import shuai.webmail.WebmailServer;
import shuai.webmail.managers.ErrorManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;

public class DispatchServlet extends HttpServlet {
	public void doGet(HttpServletRequest request,
					  HttpServletResponse response)
		throws ServletException, IOException
	{
		String uri = request.getRequestURI();
		Page p = createPage(uri, request, response);
		if ( p==null ) {
			response.sendRedirect("/files/notFoundError.html");
			return;
		}
		response.setContentType("text/html");
		p.generate();
	}



	public Page createPage(String uri,
						   HttpServletRequest request,
						   HttpServletResponse response)
            throws IOException
	{
		Class pageClass = WebmailServer.mapping.get(uri);
		try {
			Constructor<Page> ctor = pageClass.getConstructor(HttpServletRequest.class,
															  HttpServletResponse.class);
			return ctor.newInstance(request, response);
		}
		catch (Exception e) {
            response.sendRedirect("/files/internalError.html"); //page,class not properly defined
			ErrorManager.instance().error(e);
		}
		return null;
	}

}
