package shuai.webmail.pages;


import org.stringtemplate.v4.ST;
import shuai.webmail.entities.Account;
import shuai.webmail.entities.Email;
import shuai.webmail.entities.Incoming;
import shuai.webmail.entities.User;
import shuai.webmail.managers.EmailManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomePage extends Page{
    public HomePage(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    public void verify() {
        if(request.getSession().getAttribute("user")==null || request.getSession().getAttribute("account")==null){
            try{
                response.sendRedirect("/");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public ST body() {
        User user = (User) request.getSession().getAttribute("user");
        Account account = (Account) request.getSession().getAttribute("account");
        int label;
        if(request.getParameter("folder")!=null) label = Integer.parseInt(request.getParameter("folder"));
        else label=0;
        ST home = templates.getInstanceOf("home");
        ST leftbar = templates.getInstanceOf("home_leftsidebar");
        ST center = templates.getInstanceOf("home_center");
        ST table = templates.getInstanceOf("home_tabledisplay");
        ArrayList<Email> mails = new ArrayList<Email>();
        try{
            mails = EmailManager.mailList(account,label);
            EmailManager.countMails(account);
        }catch(SQLException e){
            e.printStackTrace();
        }
        int[] counts = new int[account.folders.size];
        for (int i=0; i< account.folders.size; i++){
            counts[i] = account.folders.myfolders.get(i).count;
        }

        if(user != null & account != null){
            home.add("user", user.getName());
            home.add("account", account.getEmailAddress());
            leftbar.add("account",account.getEmailAddress());
            leftbar.add("count0", counts[0] );
            leftbar.add("count1", counts[1] );
            leftbar.add("count2", counts[2] );
            leftbar.add("count3", counts[3] );
            center.add("account", account.getEmailAddress());
            table.add("maillist", mails);
            center.add("table", table);
            home.add("leftbar", leftbar);
            home.add("center",center);
            return home;
        }else{
            return null;
        }
    }

    @Override
    public ST getTitle() {
        return new ST("home page");
    }
}
