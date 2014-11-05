package shuai.webmail.mail_services;

import shuai.webmail.entities.Email;
import shuai.webmail.entities.SMTPAccount;

import java.io.IOException;

/**
 * Created by ivy on 11/4/14.
 */
public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        SMTPAccount gmail = new SMTPAccount("smtp.gmail.com", 465, true, "ivyandscorpio", "624426@ivy!!!", "ivyandscorpio@gmail.com");

        Email email = new Email();
        email.setSender("ivyandscorpio@gmail.com;");
        email.setRecipient("suxue1122@gmail.com");
        email.setSubject("SMTP server test");
        email.setBody("Hello from my own SMTP Service");

        SMTPService newOutGoing = new SMTPService(email, gmail);
        newOutGoing.send();
    }

}
