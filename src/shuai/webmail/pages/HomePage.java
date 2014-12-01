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
        //get user and accounts information from session
        User user = (User) request.getSession().getAttribute("user");
        Account primary_account = (Account) request.getSession().getAttribute("account");
        Account second_account = null;
        if(request.getSession().getAttribute("second_account")!=null) second_account = (Account) request.getSession().getAttribute("second_account");

        //account selection
        int chooseAccount = 1;
        if(request.getParameter("accountNum")!=null) chooseAccount = Integer.parseInt(request.getParameter("accountNum"));
        Account accountToShow;
        if(chooseAccount != 1){
            accountToShow = second_account;
            request.getSession().setAttribute("accountToShow", second_account);
        }
        else accountToShow = primary_account;
        if(accountToShow == null){
            try{
                response.sendRedirect("/");
            }catch(IOException e){
                e.printStackTrace();
            }finally{
                return null;
            }
        }

        //folder selection
        int label=0; //inbox by default
        if(request.getParameter("folder")!=null) label = Integer.parseInt(request.getParameter("folder"));

        //search action
        String keyword = request.getParameter("keyword"); // null if unspecified
        String by = request.getParameter("by"); // null if unspecified

        //sort action
        String sortby = request.getParameter("sortby"); // null if unspecified


        //Construct String Templates;
        ST home = templates.getInstanceOf("home");
        ST navbar = templates.getInstanceOf("home_navbar");
        ST center = templates.getInstanceOf("home_center");
        ST table = templates.getInstanceOf("home_tabledisplay");

        //generate left side bar according to account, mails in folders counted
        //generate search row according to account,
        LeftSideBar leftSideBar = new LeftSideBar(accountToShow);
        SearchRow searchRow = new SearchRow(accountToShow);
        try {
            leftSideBar = LeftSideBar.generateBar(accountToShow);
            searchRow = SearchRow.generateSearchRow(accountToShow);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //generate the mail lists to display in the main table, according to the account, as well as search/sort action if there is any
        ArrayList<Email> mails = new ArrayList<Email>();
        if(keyword == null || keyword.equals("")){ //not a search result
            if(sortby == null || sortby.equals("time")){ //not a sort result or sort by default(Date)
                try{
                    mails = EmailManager.mailList(accountToShow,label); // show all mails in the folder
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }else{  // a sort result by Sender or Subject
                try{
                    mails = EmailManager.mailList(accountToShow, label, sortby); // show all mails in the folder sorted by a field
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }

        }else{   //a search result
            try{
                mails = EmailManager.mailList(accountToShow, keyword, by); //show all mails returned by linear search
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

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

    }

    @Override
    public ST getTitle() {
        return new ST("home page");
    }
}
