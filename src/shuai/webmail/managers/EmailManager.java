package shuai.webmail.managers;


import shuai.webmail.entities.Account;
import shuai.webmail.entities.Incoming;
import shuai.webmail.entities.Outgoing;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static shuai.webmail.db_services.DBConnection.*;

/**
 * Created by ivy on 10/27/14.
 */
public class EmailManager {

    public static void addOutgoing(Outgoing email)throws SQLException{
        String clause = "INSERT INTO outgoing(sender, recipient, subject, body, sent) VALUES(?, ?, ?, ? ,?)";
        PreparedStatement query = db.prepareStatement(clause);
        query.setString(1, email.getSender());
        query.setString(2, email.getRecipient());
        query.setString(3, email.getSubject());
        query.setString(4, email.getBody());
        query.setString(5, String.valueOf(email.isSent()));
        query.executeUpdate();
    }

    public static void addIncoming(Incoming email) throws SQLException{
        String clause = "INSERT OR IGNORE INTO incoming(id, sender, recipient, subject, body) VALUES(?, ?, ?, ? ,?)";
        PreparedStatement query = db.prepareStatement(clause);
        query.setString(1, email.getID());
        query.setString(2, email.getSender());
        query.setString(3, email.getRecipient());
        query.setString(4, email.getSubject());
        query.setString(5, email.getBody());
        query.executeUpdate();
    }
/*
    if(s.startsWith("Date:")){
        time = s.substring(s.indexOf(":")+1);
        Date date = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        time = dateFormat.format(date);
    }*/


    public static ArrayList<Incoming> inboxMails(Account account) throws SQLException{
        String clause = "SELECT sender, subject, body, time FROM incoming WHERE recipient = ? ORDER BY time DESC";
        PreparedStatement query = db.prepareStatement(clause);
        query.setString(1, account.getEmailAddress());
        ResultSet result = query.executeQuery();
        ArrayList<Incoming> inboxMails = new ArrayList<Incoming>();
        while (result.next()){
            Incoming mail = new Incoming();
            mail.setSender(result.getString(1));
            mail.setSubject(result.getString(2));
            mail.setBody(result.getString(3));
            mail.setTime(result.getString(4));
            inboxMails.add(mail);
        }
        result.close();
        return inboxMails;

    }


}
