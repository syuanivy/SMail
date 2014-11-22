package shuai.webmail.managers;

import shuai.webmail.db_services.DBConnection;
import shuai.webmail.entities.Incoming;
import shuai.webmail.entities.Outgoing;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        int result = query.executeUpdate();
    }

    public static void addIncoming(Incoming email) throws SQLException{
        String clause = "INSERT INTO incoming(id, sender, recipient, subject, body) VALUES(?, ?, ?, ? ,?)";
        PreparedStatement query = db.prepareStatement(clause);
        query.setString(1, email.getID());
        query.setString(2, email.getSender());
        query.setString(3, email.getRecipient());
        query.setString(4, email.getSubject());
        query.setString(5, email.getBody());

        int result = query.executeUpdate();

    }

}
