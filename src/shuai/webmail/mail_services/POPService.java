package shuai.webmail.mail_services;

import shuai.webmail.entities.Account;
import shuai.webmail.entities.Email;

import java.io.IOException;

/**
 * Created by ivy on 11/5/14.
 */
public class POPService {
    private Account account;
    private final static int POP_PORT = 110;
    private final static int POP_SSL_PORT = 995;


    public POPService(Account account) {
        this.account = account;
    }

    public void retrieve() throws IOException {
        if(portVerified(account.getPopPort())){
            POPClient client = new POPClient(account);
            client.retrieveEmail();
        } else {
            System.err.println("Unknown POP port = " + account.getPopPort());
        }

    }

    public boolean portVerified (int port){
        if(port==POP_PORT || port==POP_SSL_PORT) return true;
        else return false;
    }

}
