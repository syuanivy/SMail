package shuai.webmail;

import shuai.webmail.db_services.DBConnection;
import shuai.webmail.misc.STListener;
import shuai.webmail.pages.*;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import shuai.webmail.processors.ComposeProcessor;
import shuai.webmail.processors.FetchProcessor;
import shuai.webmail.processors.LoginProcessor;
import shuai.webmail.processors.RegisterProcessor;


import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class WebmailServer {
	public static final String WEBMAIL_TEMPLATES_ROOT = "resources/shuai/webmail/st";

	public static final STListener stListener = new STListener();

	public static Map<String,Class> mapping = new HashMap<String, Class>();
	static {
		mapping.put("/", LoginPage.class);
        mapping.put("/login", LoginPage.class);
        mapping.put("/registerprocessor", RegisterProcessor.class);
        mapping.put("/register", RegisterPage.class);
        mapping.put("/welcome", WelcomePage.class);
        mapping.put("/userinfo", UserInfoPage.class);
        mapping.put("/loginprocessor", LoginProcessor.class);
        mapping.put("/inbox", InboxPage.class);
        mapping.put("/home", InboxPage.class);
        mapping.put("/compose", ComposePage.class);
        mapping.put("/composeprocessor", ComposeProcessor.class);
        mapping.put("/logout", LogoutPage.class);
        mapping.put("/home/fetch", FetchProcessor.class);


	}

	public static void main(String[] args) throws Exception {
		//Make sure there are two program arguments set in Run-Edit Configuration:
        //In this case are the paths of the static-pages and shuai.webmail.log folders
        if ( args.length<2 ) {
			System.err.println("java shuai.webmail.Server static-files-dir shuai.webmail.log-dir");
			System.exit(1);
		}
		String staticFilesDir = args[0];     // static-pages
		String logDir = args[1];             //shuai.webmail.log

        //Create a server
        Server server = new Server(8080);

		//set the context handler for the server, "/"
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);


		// add the Dispatch Servlet at "/dynamic/*"
        ServletHolder holderDynamic = new ServletHolder("dynamic", DispatchServlet.class);
		context.addServlet(holderDynamic, "/*");


        // add the static-home servlet, specifying "/home/" content mapped to the homePath
        ServletHolder holderHome = new ServletHolder("static-home", DefaultServlet.class);
        holderHome.setInitParameter("resourceBase",staticFilesDir);
        holderHome.setInitParameter("dirAllowed","true");
        holderHome.setInitParameter("pathInfoOnly","true");
		context.addServlet(holderHome, "/files/*");

        // Lastly, the default servlet for root content (always needed, to satisfy servlet spec)
        // It is important that this is last.
        ServletHolder holderPwd = new ServletHolder("default", DefaultServlet.class);
        holderPwd.setInitParameter("resourceBase","/tmp/foo");
        holderPwd.setInitParameter("dirAllowed","true");
		context.addServlet(holderPwd, "/");

		// shuai.webmail.log using NCSA (common shuai.webmail.log format)
		// http://en.wikipedia.org/wiki/Common_Log_Format
		NCSARequestLog requestLog = new NCSARequestLog();
		requestLog.setFilename(logDir + "/yyyy_mm_dd.request.shuai.webmail.log");
		requestLog.setFilenameDateFormat("yyyy_MM_dd");
		requestLog.setRetainDays(90);
		requestLog.setAppend(true);
		requestLog.setExtended(true);
		requestLog.setLogCookies(false);
		requestLog.setLogTimeZone("GMT");
		RequestLogHandler requestLogHandler = new RequestLogHandler();
		requestLogHandler.setRequestLog(requestLog);
		requestLogHandler.setServer(server);
        //

        Connection db = DBConnection.getDBConnection();


		server.start();
		server.join();
	}
}
