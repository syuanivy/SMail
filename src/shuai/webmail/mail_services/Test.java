package shuai.webmail.mail_services;

import shuai.webmail.db_services.DBConnection;
import shuai.webmail.entities.Account;
import shuai.webmail.entities.Email;
import shuai.webmail.entities.Outgoing;
import shuai.webmail.managers.EmailManager;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by ivy on 11/4/14.
 */
public class Test {
    public static void main(String[] args) throws IOException, InterruptedException,SQLException {
        Connection db = DBConnection.getDBConnection();

        Email test= EmailManager.findEmail("20","sent");
        System.out.println(test.hasAttachement() + " /" + test.getLabel() + " /" + test.getTime());

        Email test34= EmailManager.findEmail("34","sent");
        System.out.println(test34.hasAttachement() + " /" + test34.getLabel() + " /" + test34.getTime());
/*
        String s = "teststring";
        String encoded = new BASE64Encoder().encode(s.getBytes());
        byte[] decodedbytes = new BASE64Decoder().decodeBuffer(encoded);
        String decoded = new String(decodedbytes,"UTF-8");
        System.out.println(encoded);
        System.out.println(decoded);
*/

/*
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
*/

    }

}
