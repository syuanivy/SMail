package shuai.webmail.managers;


import shuai.webmail.entities.Account;
import shuai.webmail.entities.Email;
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
        String clause = "INSERT INTO outgoing(sender, recipient, subject, body, attached,label) VALUES(?, ?, ?, ? ,?)";
        PreparedStatement query = db.prepareStatement(clause);
        query.setString(1, email.getSender());
        query.setString(2, email.getRecipient());
        query.setString(3, email.getSubject());
        query.setString(4, email.getBody());
        query.setString(5, String.valueOf(email.hasAttachement()));
        query.setString(6, String.valueOf(email.isSent()));
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

    public static Email findEmail(String id, String foldername) throws SQLException{
        Email email = new Email();
        String clause = new String();
        if(foldername.equals("inbox")) clause = "SELECT id, sender,recipient, subject, body, time,label FROM incoming WHERE id = ?";
        if(foldername.equals("sent")) clause = "SELECT id, sender, recipient, subject, body, time,label FROM outgoing WHERE id = ?";
        PreparedStatement query = db.prepareStatement(clause);
        query.setString(1, id);
        ResultSet result = query.executeQuery();
        while (result.next()){
            email.setID(result.getString(1));
            email.setSender(result.getString(2));
            email.setRecipient(result.getString(3));
            email.setSubject(result.getString(4));
            email.setBody(result.getString(5));
            email.setTime(result.getString(6));
            email.setLabel(result.getInt(7));
            result.close();
            return email;
        }

        return null;
    }
public static String findFolder(String label){
    String foldername = new String();
    if(label.equals("0")) foldername = "inbox";
    if(label.equals("1")) foldername = "sent";
    if(label.equals("2")) foldername = "draft";
    if(label.equals("3")) foldername = "trash";
    return foldername;
}



/*
    if(s.startsWith("Date:")){
        time = s.substring(s.indexOf(":")+1);
        Date date = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        time = dateFormat.format(date);
    }*/


    public static ArrayList<Email> mailList(Account account, String foldername) throws SQLException{
        String clause = new String();
/*
        switch (foldername) {
            case "inbox":
                clause = "SELECT id, sender, subject, body, time FROM incoming WHERE recipient = ? ORDER BY time DESC";
            case "sent":
                clause = "SELECT id, sender, subject, body, time FROM outgoing WHERE recipient = ? ORDER BY time DESC";
        }*/
        if(foldername.equals("inbox")) clause = "SELECT id, sender, subject, body, time, label FROM incoming WHERE recipient = ? ORDER BY time DESC";
        if(foldername.equals("sent")) clause = "SELECT id, sender, subject, body, time, label FROM outgoing WHERE sender = ? AND label = 1 ORDER BY time DESC";

        PreparedStatement query = db.prepareStatement(clause);
        query.setString(1, account.getEmailAddress());
        ResultSet result = query.executeQuery();
        ArrayList<Email> mailList = new ArrayList<Email>();
        while (result.next()){
            Email mail = new Email();
            mail.setID(result.getString(1));
            mail.setSender(result.getString(2));
            mail.setSubject(result.getString(3));
            mail.setBody(result.getString(4));
            mail.setTime(result.getString(5));
            mail.setLabel(result.getInt(6));
            mailList.add(mail);
        }
        result.close();
        return mailList;

    }


}
