package shuai.webmail.mail_services;

import shuai.webmail.entities.Account;
import shuai.webmail.entities.Outgoing;

import java.io.IOException;

/**
 * Created by ivy on 11/4/14.
 */
public class SMTPService {
    private Outgoing Outgoing;
    private Account account;
    private final static int SMTP_PORT = 25;
    private final static int SMTP_SSL_PORT = 465;

    public SMTPService(Outgoing Outgoing, Account account) {
        this.Outgoing = Outgoing;
        this.account = account;
    }

    public void send() throws IOException{
        if(portVerified(465)){
            SMTPClient client = new SMTPClient(account);
            client.sendEmail(Outgoing);
        } else {
            System.err.println("Unknown SMTP port = " + account.getSmtpPort());
        }

    }

    public boolean portVerified (int port){
        if(port==SMTP_PORT || port== SMTP_SSL_PORT) return true;
        else return false;
    }

}
