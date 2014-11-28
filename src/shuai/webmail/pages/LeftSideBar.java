package shuai.webmail.pages;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;
import shuai.webmail.WebmailServer;
import shuai.webmail.entities.Account;
import shuai.webmail.managers.EmailManager;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ivy on 11/28/14.
 */
public class LeftSideBar {
    static STGroup templates = new STGroupDir(WebmailServer.WEBMAIL_TEMPLATES_ROOT); // call unload() to wack the cache
    static {
        templates.setListener(WebmailServer.stListener);
        templates.delimiterStartChar = '$';
        templates.delimiterStopChar = '$';
    }
    public ST leftbar = templates.getInstanceOf("home_leftsidebar");
    public Account account;

    public LeftSideBar(Account account) throws SQLException{
        this.account = account;
        ArrayList<Account.MyFolder> myfolders = account.folders.myfolders;
        EmailManager.countMails(account);
        int[] builtin_counts = new int[4];
        for (int i=0; i< 4; i++){
            builtin_counts[i] = account.folders.myfolders.get(i).count;
        }
        ArrayList<Account.MyFolder> userdefinedlist = new ArrayList<>();
        if(myfolders.size()>4) userdefinedlist= (ArrayList<Account.MyFolder>) myfolders.subList(4,myfolders.size()-1);
        leftbar.add("account",account.getEmailAddress());
        leftbar.add("count0", builtin_counts[0] );
        leftbar.add("count1", builtin_counts[1] );
        leftbar.add("count2", builtin_counts[2] );
        leftbar.add("count3", builtin_counts[3] );
        leftbar.add("userdefinedlist", userdefinedlist );
    }



}
