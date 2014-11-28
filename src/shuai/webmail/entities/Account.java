package shuai.webmail.entities;

import shuai.webmail.managers.EmailManager;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by ivy on 11/4/14.
 */
public class Account {
    private String emailAddress;
    private String smtpServer;
    private int smtpPort;
    private String popServer;
    private int popPort;
    private int SSL;//TODO: boolean, by checkbox
    private String userName;
    private String password;
    private String localUser;
    public Folders folders = new Folders();


    public Account(){}
    public Account(String email, String smtp, int smtpPort, String pop, int popPOrt,
                   int SSL, String username, String password, String localUser){
        this.emailAddress= email;
        this.smtpServer = smtp;
        this.smtpPort = smtpPort;
        this.popServer = pop;
        this.popPort = popPOrt;
        this.SSL = SSL;
        this.userName = username;
        this.password = password;
        this.localUser = localUser;

    }
    public class MyFolder{
        public int label;
        public String foldername;
        public int count=0;
        public MyFolder(int label, String foldername){
            this.label = label;
            this.foldername = foldername;
        }

        public void setCount(int count){this.count = count;}
    }

    public class Folders{
        public ArrayList<MyFolder> myfolders = new ArrayList<MyFolder>();
        public int size = 4;
        public Folders(){
            this.myfolders.add(new MyFolder(0,"inbox"));// four built in folders
            this.myfolders.add(new MyFolder(1,"sent"));
            this.myfolders.add(new MyFolder(2,"draft"));
            this.myfolders.add(new MyFolder(4,"trash"));
        }

        public void newFolder(String foldername) throws SQLException{
            int newLabel = EmailManager.addFolder(foldername);
            this.myfolders.add(new MyFolder(newLabel, foldername));
            this.size++;
        }


    }

    public String getSmtpServer() {return smtpServer;}
    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }
    public int getSmtpPort() {return smtpPort;}
    public void setSmtpPort(int port) {
        this.smtpPort = port;
    }

    public String getPopServer() {return popServer;}
    public void setPopServer(String popServer) {
        this.popServer = popServer;
    }
    public int getPopPort() {return popPort;}
    public void setPopPort(int port) {
        this.popPort = port;
    }

    public int isEncryption() {
        return SSL;
    }
    public void setEncryption(int isEncrypted) {
        this.SSL = isEncrypted;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {this.password = password;}
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getLocalUser() {
        return localUser;
    }
    public void setLocalUser(String localUser) {
        this.localUser = localUser;
    }



}
