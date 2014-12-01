package shuai.webmail.entities;

import shuai.webmail.managers.EmailManager;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ivy on 11/28/14.
 */
public class Folders {
    public ArrayList<MyFolder> myfolders = new ArrayList<MyFolder>();
    public int size = 4;
    public Folders(){
        this.myfolders.add(new MyFolder(0,"Inbox"));// four built in folders
        this.myfolders.add(new MyFolder(1,"Sent"));
        this.myfolders.add(new MyFolder(2,"Draft"));
        this.myfolders.add(new MyFolder(4,"Trash"));
    }

    public void newFolder(String foldername) throws SQLException {
        int newLabel = EmailManager.addFolder(foldername);
        this.myfolders.add(new MyFolder(newLabel, foldername));
        this.size++;
    }


}
