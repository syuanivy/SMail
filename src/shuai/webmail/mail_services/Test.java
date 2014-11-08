package shuai.webmail.mail_services;

import shuai.webmail.entities.Account;
import shuai.webmail.entities.Email;

import java.io.IOException;

/**
 * Created by ivy on 11/4/14.
 */
public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        Account gmail = new Account("smtp.gmail.com", 465, "pop.gmail.com", 995, true, "syuanivy", "624426@ivy!!!", "syuanivy@gmail.com");

        Email email = new Email();
        email.setSender("syuanivy@gmail.com;");
        email.setRecipient("wangxitao_work@126.com ");
        email.setSubject("Are you still at school?");
        email.setBody("Sent from my SMTP :)");

      //  SMTPService newOutgoing = new SMTPService(email, gmail);
       // newOutgoing.send();
        POPService inbox = new POPService(gmail);
        inbox.retrieve();
    }

}
