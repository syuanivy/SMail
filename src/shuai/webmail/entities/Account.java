package shuai.webmail.entities;

/**
 * Created by ivy on 11/4/14.
 */
public class Account {
    private String smtpServer;
    private int smtpPort;
    private String popServer;
    private int popPort;
    private boolean SSL;
    private String userName;
    private String password;
    private String emailAddress;

    public Account(String smtpServer, int smtpPort, String popServer, int popPort, boolean SSL, String userName, String password, String emailAddress) {
        this.smtpServer = smtpServer;
        this.smtpPort = smtpPort;
        this.popServer = popServer;
        this.popPort = popPort;
        this.SSL = SSL;
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
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

    public boolean isEncryption() {
        return SSL;
    }
    public void setEncryption(boolean isEncrypted) {
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



}
