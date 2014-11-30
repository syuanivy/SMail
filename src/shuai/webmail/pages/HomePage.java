package shuai.webmail.pages;


import org.stringtemplate.v4.ST;
import shuai.webmail.entities.Account;
import shuai.webmail.entities.Email;
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
        Account primary_account = (Account) request.getSession().getAttribute("account");
        Account second_account = null;
        if(request.getSession().getAttribute("second_account")!=null) second_account = (Account) request.getSession().getAttribute("second_account");
        int chooseAccount = 1;

        if(request.getParameter("accountNum")!=null) chooseAccount = Integer.parseInt(request.getParameter("accountNum"));

        int label=0;
        if(request.getParameter("folder")!=null) label = Integer.parseInt(request.getParameter("folder"));


        ST home = templates.getInstanceOf("home");

        ST navbar = templates.getInstanceOf("home_navbar");

        Account accountToShow;
        if(chooseAccount != 1){
            accountToShow = second_account;
            request.getSession().setAttribute("accountToShow", second_account);
        }
        else accountToShow = primary_account;

        LeftSideBar leftSideBar = new LeftSideBar(accountToShow);
        SearchRow searchRow = new SearchRow(accountToShow);
        try {
             leftSideBar = LeftSideBar.generateBar(accountToShow);
             searchRow = SearchRow.generateSearchRow(accountToShow);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ST center = templates.getInstanceOf("home_center");

        ST table = templates.getInstanceOf("home_tabledisplay");
        ArrayList<Email> mails = new ArrayList<Email>();
        try{
            mails = EmailManager.mailList(accountToShow,label);
        }catch(SQLException e){
            e.printStackTrace();
        }

        if(user != null & accountToShow != null & leftSideBar != null){
            home.add("user", user.getName());
            home.add("account", accountToShow.getEmailAddress());
            navbar.add("user", user);
            navbar.add("account", primary_account);
            navbar.add("second_account", second_account);
            center.add("account", accountToShow.getEmailAddress());
            center.add("searchrow", searchRow.searchrow);
            table.add("maillist", mails);
            center.add("table", table);
            home.add("navbar",navbar);
            home.add("leftbar", leftSideBar.leftbar);
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
