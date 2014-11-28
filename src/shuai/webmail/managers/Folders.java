package shuai.webmail.managers;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ivy on 11/28/14.
 */
public class Folders {
    public ArrayList<MyFolder> myfolders = new ArrayList<MyFolder>();
    public int size = 4;
    public Folders(){
        this.myfolders.add(new MyFolder(0,"inbox"));// four built in folders
        this.myfolders.add(new MyFolder(1,"sent"));
        this.myfolders.add(new MyFolder(2,"draft"));
        this.myfolders.add(new MyFolder(4,"trash"));
    }

    public void newFolder(String foldername) throws SQLException {
        int newLabel = EmailManager.addFolder(foldername);
        this.myfolders.add(new MyFolder(newLabel, foldername));
        this.size++;
    }


}
