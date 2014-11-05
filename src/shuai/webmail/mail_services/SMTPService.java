package shuai.webmail.mail_services;

import shuai.webmail.entities.Email;
import shuai.webmail.entities.SMTPAccount;

import java.io.IOException;

/**
 * Created by ivy on 11/4/14.
 */
public class SMTPService {
    private Email email;
    private SMTPAccount account;
    private final static int SMTP_PORT = 25;
    private final static int SMTP_SSL_PORT = 465;

    public SMTPService(Email email, SMTPAccount account) {
        this.email = email;
        this.account = account;
    }

    public void send() throws IOException{
        if(portVerified(account.getPort())){
            SMTPClient client = new SMTPClient(account);
            client.sendEMail(email);
        } else {
            System.err.println("Unknown SMTP port = " + account.getPort());
        }

    }

    public boolean portVerified (int port){
        if(port==SMTP_PORT || port== SMTP_SSL_PORT) return true;
        else return false;
    }

}
