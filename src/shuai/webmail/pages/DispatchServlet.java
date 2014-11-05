package shuai.webmail.pages;

import shuai.webmail.WebmailServer;
import shuai.webmail.managers.ErrorManager;
import shuai.webmail.processors.PostProcessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.SQLException;

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
        if(pageClass == null){
            response.sendRedirect("/files/notFoundError.html");
        }
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

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        PostProcessor processor = createProcessor(uri, request, response);
        if ( processor==null ) {
            response.sendRedirect("/files/notFoundError.html"); // change to invalid input error
            return;
        }
        try{
            processor.processPost();
        }catch(SQLException e){
            ErrorManager.instance().error(e);
        }

    }

    public PostProcessor createProcessor(String uri,
                                         HttpServletRequest request,
                                         HttpServletResponse response) throws IOException{
        Class processorClass = WebmailServer.mapping.get(uri);
        try {
            Constructor<PostProcessor> ctor = processorClass.getConstructor(HttpServletRequest.class,
                    HttpServletResponse.class);
            return ctor.newInstance(request, response);
        }
        catch (Exception e) {
            response.sendRedirect("/files/internalError.html"); //processor class not properly defined
            ErrorManager.instance().error(e);
        }
        return null;
    }


}
