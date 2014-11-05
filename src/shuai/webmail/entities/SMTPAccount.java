package shuai.webmail.entities;

/**
 * Created by ivy on 11/4/14.
 */
public class SMTPAccount {
    private String smtpServer;
    private int port;
    private boolean SSL;
    private String userName;
    private String password;
    private String emailAddress;

    public SMTPAccount(String smtpServer, int port, boolean SSL, String userName, String password, String emailAddress) {
        this.smtpServer = smtpServer;
        this.port = port;
        this.SSL = SSL;
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public String getSmtpServer() {
        return smtpServer;
    }
    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
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
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public boolean isEncryption() {
        return SSL;
    }
    public void setEncryption(boolean isEncrypted) {
        this.SSL = isEncrypted;
    }
}
