package shuai.webmail.pages;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;
import shuai.webmail.WebmailServer;
import shuai.webmail.entities.Account;
import shuai.webmail.entities.MyFolder;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ivy on 11/28/14.
 */
public class SearchRow {
    static STGroup templates = new STGroupDir(WebmailServer.WEBMAIL_TEMPLATES_ROOT); // call unload() to wack the cache
    static {
        templates.setListener(WebmailServer.stListener);
        templates.delimiterStartChar = '$';
        templates.delimiterStopChar = '$';
    }
    public ST searchrow = templates.getInstanceOf("home_searchrow");
    public Account account;

    public SearchRow(Account account){
        this.account = account;

    }
    public static SearchRow generateSearchRow(Account account) throws SQLException {
        if (account==null) return null;
        SearchRow searchRow = new SearchRow(account);
        ArrayList<MyFolder> myfolders = account.folders.myfolders;
/*        ArrayList<String> foldernames = new ArrayList<>();
        for (int i=0 ; i<myfolders.size(); i++){
            foldernames.add(myfolders.get(i).foldername);
        }*/
        searchRow.searchrow.add("account", account);
        searchRow.searchrow.add("folders", myfolders);

        return searchRow;

    }


}
