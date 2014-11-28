package shuai.webmail.managers;


import shuai.webmail.entities.Account;
import shuai.webmail.entities.Email;
import shuai.webmail.entities.Incoming;
import shuai.webmail.entities.Outgoing;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import static shuai.webmail.db_services.DBConnection.*;

/**
 * Created by ivy on 10/27/14.
 */
public class EmailManager {
    //Count the number of mails in built in folders
    public static void countMails(Account account) throws SQLException {
        Account.Folders folderInfo = account.folders;
        int numFolders = folderInfo.size;
        int[] builtIn = countBuiltIn(account);
        for (int i=0; i<4 ; i++){
            folderInfo.myfolders.get(i).setCount(builtIn[i]);
        }
        //count user-defined folders
        if(numFolders>4){
            for (Account.MyFolder folder:folderInfo.myfolders.subList(4,numFolders-1)){
                String[] clauses=new String[2];
                clauses[0] = "SELECT COUNT(*) FROM incoming WHERE label=? AND recipient=?";
                clauses[1] = "SELECT COUNT(*) FROM outgoing WHERE label=? AND sender=?";
                int count=0;
                for (String clause: clauses){
                    PreparedStatement query = db.prepareStatement(clause);
                    query.setInt(1,folder.label);
                    query.setString(2,account.getEmailAddress());
                    ResultSet result = query.executeQuery();
                    if(result.next()) count += result.getInt(1);
                    result.close();
                }
                folder.setCount(count);
            }
        }
    }

    public static int[] countBuiltIn(Account account) throws SQLException{

            int[] builtIn = new int[4];
            String[] clauses = new String[5];
            clauses[0] = "SELECT COUNT(*) FROM incoming WHERE label=0 AND recipient=?";
            clauses[1] = "SELECT COUNT(*) FROM outgoing WHERE label=1 AND sender=?";
            clauses[2] = "SELECT COUNT(*) FROM outgoing WHERE label=2 AND sender=?";
            clauses[3]  = "SELECT COUNT(*) FROM incoming WHERE label=4 AND recipient=?";
            clauses[4] = "SELECT COUNT(*) FROM outgoing WHERE label=4 AND sender=? ";
            for(int i=0;i<4;i++){
                PreparedStatement query = db.prepareStatement(clauses[i]);
                query.setString(1,account.getEmailAddress());
                ResultSet result = query.executeQuery();
                if(result.next()) builtIn[i] = result.getInt(1);
                result.close();
            }
            PreparedStatement query = db.prepareStatement(clauses[4]);
            query.setString(1,account.getEmailAddress());
            ResultSet result = query.executeQuery();
            if(result.next()) builtIn[3]+= result.getInt(1);
            return builtIn;
        }

    public static void addOutgoing(Outgoing email) throws SQLException{
        String clause = "INSERT INTO outgoing(sender, recipient, subject, body, label) VALUES(?, ?, ?, ? ,?)";
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

    public static Email findEmail(String id, int label ) throws SQLException{
        Email email = new Email();
        ResultSet emailContent=getEmailContent(id, label);
        while (emailContent.next()){
            email.setID(emailContent.getString(1));
            email.setSender(emailContent.getString(2));
            email.setRecipient(emailContent.getString(3));
            email.setSubject(emailContent.getString(4));
            email.setBody(emailContent.getString(5));
            email.setTime(emailContent.getString(6));
            email.setAttachment(emailContent.getInt(7));
            email.setLabel(emailContent.getInt(8));
            emailContent.close();
            return email;
        }
        return null;
    }

    private static ResultSet getEmailContent(String id, int label) throws SQLException {
        String clause = new String();
        if(label<=3){
            switch(label){//built in folders
                case 0: clause = "SELECT id, sender,recipient, subject, body, time, attached, label FROM incoming WHERE id = ?";break;
                case 1: clause = "SELECT id, sender,recipient, subject, body, time, attached, label FROM outgoing WHERE id = ?";break;
                case 2: clause = "SELECT id, sender,recipient, subject, body, time, attached, label FROM outgoing WHERE id = ?";break;
                case 3: clause = "SELECT id, sender,recipient, subject, body, time, attached, label FROM incoming WHERE id = ?";break;
            }
            PreparedStatement query = db.prepareStatement(clause);
            db.prepareStatement(clause);
            query.setString(1, id);
            ResultSet emailContent = query.executeQuery();
            return emailContent;
        }else{//trash or user-defined folders
            clause = "SELECT id, sender, recipient, subject, body, time, attached, label FROM incoming WHERE id = ? AND label = ?" +
                    "UNION SELECT id, sender, recipient, subject, body, time, attached, label FROM outgoing WHERE id = ? AND label = ? ORDER BY time DESC";
            PreparedStatement query = db.prepareStatement(clause);
            db.prepareStatement(clause);
            query.setString(1, id);
            query.setInt(2,label);
            query.setString(3,id);
            query.setInt(4, label);
            ResultSet emailContent = query.executeQuery();
            return emailContent;
        }
    }


    public static ArrayList<Email> mailList(Account account, int label) throws SQLException{
        ResultSet folderContent = getFolderContent(account, label);
        ArrayList<Email> mailList = new ArrayList<Email>();
        while (folderContent.next()){
            Email mail = new Email();
            mail.setID(folderContent.getString(1));
            mail.setSender(folderContent.getString(2));
            mail.setRecipient(folderContent.getString(3));
            mail.setSubject(folderContent.getString(4));
            mail.setBody(folderContent.getString(5));
            mail.setTime(folderContent.getString(6));
            mail.setAttachment(folderContent.getInt(7));
            mail.setLabel(folderContent.getInt(8));
            mailList.add(mail);
        }
        folderContent.close();
        return mailList;

    }

    private static ResultSet getFolderContent(Account account, int label) throws SQLException {
        String clause = new String();
        if(label<=3){  //inbox/sender/draft/unread, from single email table: either incoming or outgoing
            switch (label) {
                case 0://inbox
                    clause = "SELECT id, sender, recipient, subject, body, time,attached, label FROM incoming WHERE recipient = ? ORDER BY time DESC"; break;
                case 1://sent
                    clause = "SELECT id, sender, recipient, subject, body, time,attached, label FROM outgoing WHERE sender = ? AND label = 1 ORDER BY time DESC"; break;
                case 2://draft
                    clause = "SELECT id, sender, recipient, subject, body, time,attached, label FROM outgoing WHERE sender = ? AND label = 2 ORDER BY time DESC"; break;
                case 3://inbox read
                    clause = "SELECT id, sender, recipient, subject, body, time,attached, label FROM incoming WHERE recipient = ? AND label = 3 ORDER BY time DESC"; break;
            }
            PreparedStatement query = db.prepareStatement(clause);
            query.setString(1, account.getEmailAddress());
            ResultSet folderContent= query.executeQuery();
            return folderContent;
        }else{//trash or user-defined can be from both incoming and outgoing
            clause= "SELECT id, sender, recipient, subject, body, time, attached, label FROM outgoing WHERE sender = ? AND label=? " +
                    "UNION SELECT id, sender, recipient, subject, body, time, attached, label FROM incoming WHERE recipient = ? AND label=? ORDER BY time DESC";
            PreparedStatement query = db.prepareStatement(clause);
            query.setString(1, account.getEmailAddress());
            query.setInt(2,label);
            query.setString(3, account.getEmailAddress());
            query.setInt(4,label);
            ResultSet folderContent= query.executeQuery();
            return folderContent;
        }
    }

    //add user-defined folder
    public static int addFolder(String foldername) throws SQLException{
        String clause = "INSERT INTO label(label) VALUES(?)";
        PreparedStatement query = db.prepareStatement(clause);
        query.setString(1, foldername);
        query.executeUpdate();
        clause = "SELECT id FROM Label WHERE label=foldername";
        query = db.prepareStatement(clause);
        ResultSet result =query.executeQuery();
        int newLabel = -1;
        if(result.next()) newLabel = result.getInt(1);
        return newLabel;
    }

    //move emails between folders
    public static void changeFolder(String id, int labelBefore, int labelAfter) throws SQLException{
        String clause= new String();
        if(isIncoming(id, labelBefore)) clause = "UPDATE incoming SET label = ? WHERE id = ?";
        else clause = "UPDATE outgoing SET label = ? WHERE id = ?";
        PreparedStatement query = db.prepareStatement(clause);
        query.setInt(1, labelAfter);
        query.setString(2,id);
        query.executeUpdate();

    }

    public static boolean isIncoming(String id, int label) throws SQLException{
        if(label==0 || label==3) return true;//inbox or read
        if(label==1 || label==2) return false; // sent or draft
        else {//find by id
            PreparedStatement query = db.prepareStatement("SELECT * FROM incoming WHERE id=?");
            query.setString(1,id);
            ResultSet result = query.executeQuery();
            if(result.next()) return true;
            else return false;
        }
    }



    /*    public static String findFolderName(int label) throws SQLException{
            String foldername = new String();
            String clause = "SELECT label FROM label WHERE id = ? ";
            PreparedStatement query = db.prepareStatement(clause);
            query.setInt(1, label);
            ResultSet result= query.executeQuery();
            while (result.next()){
                foldername = result.getString(1);
            }
            return foldername;
        }*/
    /*
    public static String Dateformat(String serverDate){
        String serverFormat;
        String covertedFormat;
        if(serverDate.startsWith("Date:")){
            serverFormat = serverDate.substring(serverDate.indexOf(":")+1);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            covertedFormat = dateFormat.format(serverFormat);
            return covertedFormat;
        }
        return null;
    }
*/
}
