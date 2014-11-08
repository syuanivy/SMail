package shuai.webmail.mail_services;

import shuai.webmail.entities.Account;
import shuai.webmail.entities.Email;

import java.io.IOException;

/**
 * Created by ivy on 11/4/14.
 */
public class SMTPService {
    private Email email;
    private Account account;
    private final static int SMTP_PORT = 25;
    private final static int SMTP_SSL_PORT = 465;

    public SMTPService(Email email, Account account) {
        this.email = email;
        this.account = account;
    }

    public void send() throws IOException{
        if(portVerified(account.getSmtpPort())){
            SMTPClient client = new SMTPClient(account);
            client.sendEmail(email);
        } else {
            System.err.println("Unknown SMTP port = " + account.getSmtpPort());
        }

    }

    public boolean portVerified (int port){
        if(port==SMTP_PORT || port== SMTP_SSL_PORT) return true;
        else return false;
    }

}
