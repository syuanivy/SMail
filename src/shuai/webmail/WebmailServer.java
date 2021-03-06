package shuai.webmail;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import shuai.webmail.db_services.DBConnection;
import shuai.webmail.misc.STListener;
import shuai.webmail.pages.*;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import shuai.webmail.processors.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class WebmailServer {
    public static final String WEBMAIL_TEMPLATES_ROOT = "resources/shuai/webmail/st";

    public static final STListener stListener = new STListener();

    public static Map<String,Class> mapping = new HashMap<String, Class>();
    static {
        //Pages respond to doGET
        mapping.put("/", LoginPage.class);
        mapping.put("/login", LoginPage.class);
        mapping.put("/register", RegisterPage.class);
        mapping.put("/welcome", WelcomePage.class);
        mapping.put("/userinfo", UserInfoPage.class);
        mapping.put("/inbox", HomePage.class);
        mapping.put("/home", HomePage.class);
        mapping.put("/logout", LogoutPage.class);
        mapping.put("/display", MessageDisplayPage.class);

        //Processors respond to doPOST
        mapping.put("/loginprocessor", LoginProcessor.class);
        mapping.put("/registerprocessor", RegisterProcessor.class);
        mapping.put("/addaccount", AddAccountProcessor.class);
        mapping.put("/changePW", ChangePWProcessor.class);
        mapping.put("/home/fetch", FetchProcessor.class);
        mapping.put("/composeprocessor", ComposeProcessor.class);
        mapping.put("/save", SaveDraftProcessor.class);
        mapping.put("/home/addfolder", AddFolderProcessor.class);
        mapping.put("/home/changefolder", ChangeFolderProcessor.class);
        mapping.put("/emptytrash", EmptyTrashProcessor.class);




    }

    public static void main(String[] args) throws Exception {
        //Two program arguments:
        //1.dir of the static-pages
        //2.dir of log files
//        if ( args.length<2 ) {
//            System.err.println("java shuai.webmail.Server static-files-dir shuai.webmail.log-dir");
//            System.exit(1);
//        }

        String staticFilesDir = "resources/shuai/webmail/static-pages";     // static-pages
        String logDir = "log/Shuai/webmail";             //log


        //set the context handler for the server, "/"
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");


        String jettyDistKeystore = "resources/shuai/webmail/static-pages/keystore";
        String keystorePath =  jettyDistKeystore;
        File keystoreFile = new File(keystorePath);
        if (!keystoreFile.exists())
        {
            throw new FileNotFoundException(keystoreFile.getAbsolutePath());
        }

        // Create a basic jetty server object without declaring the port. Since
        Server server = new Server();

        // HTTP Configuration
        HttpConfiguration http_config = new HttpConfiguration();
        http_config.setSecureScheme("https");
        http_config.setSecurePort(8443);
        http_config.setOutputBufferSize(32768);

        // HTTP connector
        ServerConnector http = new ServerConnector(server,
                new HttpConnectionFactory(http_config));
        http.setPort(8080);
        http.setIdleTimeout(30000);

        // SSL Context Factory for HTTPS and SPDY
        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setKeyStorePath(keystoreFile.getAbsolutePath());
        sslContextFactory.setKeyStorePassword("OBF:1vny1zlo1x8e1vnw1vn61x8g1zlu1vn4");
        sslContextFactory.setKeyManagerPassword("OBF:1u2u1wml1z7s1z7a1wnl1u2g");

        // HTTPS Configuration

        HttpConfiguration https_config = new HttpConfiguration(http_config);
        https_config.addCustomizer(new SecureRequestCustomizer());

        // HTTPS connector
        ServerConnector https = new ServerConnector(server,
                new SslConnectionFactory(sslContextFactory, "http/1.1"),
                new HttpConnectionFactory(https_config));
        https.setPort(8443);
        https.setIdleTimeout(500000);

        // Set the connectors
        server.setConnectors(new Connector[] { http, https });



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

        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[]{context,requestLogHandler});
        server.setHandler(handlers);
        //

        Connection db = DBConnection.getDBConnection();


        server.start();
        server.join();
    }
}
