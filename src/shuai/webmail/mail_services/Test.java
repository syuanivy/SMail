package shuai.webmail.mail_services;

import shuai.webmail.entities.Account;
import shuai.webmail.entities.Outgoing;

import java.io.IOException;

/**
 * Created by ivy on 11/4/14.
 */
public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        Account gmail = new Account("syuanivy@gmail.com","smtp.gmail.com", 465, "pop.gmail.com", 995, 1, "syuanivy", "624426@ivy!!!", "ivy");


        Outgoing Outgoing = new Outgoing();
        Outgoing.setSender("syuanivy@gmail.com");
        Outgoing.setRecipient("syuanivy@gmail.com");
        Outgoing.setSubject("Open your eyes");
        Outgoing.setBody("retest");


        SMTPService newOutgoing = new SMTPService(Outgoing, gmail);
        newOutgoing.send();

        POPService inbox = new POPService(gmail);
        inbox.retrieve();

    }

}
