package shuai.webmail.pages;

import shuai.webmail.WebmailServer;
import shuai.webmail.managers.ErrorManager;
import shuai.webmail.misc.VerifyException;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Page {
	static STGroup templates = new STGroupDir(WebmailServer.WEBMAIL_TEMPLATES_ROOT); // call unload() to wack the cache
	static {
		templates.setListener(WebmailServer.stListener);
		templates.delimiterStartChar = '$';
		templates.delimiterStopChar = '$';
	}

	HttpServletRequest request;
	HttpServletResponse response;
	PrintWriter out;
	int pageNum;

	public Page(HttpServletRequest request,
				HttpServletResponse response)
	{
		this.request = request;
		this.response = response;
		try {
			out = response.getWriter();
		}
		catch (IOException ioe) {
			ErrorManager.instance().error(ioe);
		}
	}

	public void verify() throws VerifyException {
		// handle default args like page number, etc...
		// verify that arguments make sense
		// implemented by subclass typically
		// VerifyException is a custom Exception subclass
	}

	public void handleDefaultArgs() {
		// handle default args like page number, etc...
		String pageStr = request.getParameter("page");
		if ( pageStr!=null ) {
			pageNum = Integer.valueOf(pageStr);
		}
	}

	public void generate() {
		handleDefaultArgs();
		try {
			verify(); // check args before generation
			ST pageST = templates.getInstanceOf("page");
           // ST scriptST = script();
			ST bodyST = body();
           // pageST.add("script", scriptST);
			pageST.add("body", bodyST);
			pageST.add("title", getTitle());

			String page = pageST.render();
			out.print(page);
		}
		catch (VerifyException ve) {
			// redirect to error page
		}
		finally {
			out.close();
		}
	}

    //public abstract ST script();

	public abstract ST body();

	public abstract ST getTitle();
}
