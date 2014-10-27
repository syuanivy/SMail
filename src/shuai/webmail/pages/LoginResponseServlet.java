package shuai.webmail.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class LoginResponseServlet extends HttpServlet {
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        out.println("Hello "+user+" "+password+"<br><br>");
        out.println("</body></html>");
        out.close();
    }
}