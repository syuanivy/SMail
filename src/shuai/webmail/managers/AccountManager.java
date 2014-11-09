package shuai.webmail.managers;

import shuai.webmail.db_services.DBConnection;
import shuai.webmail.entities.Account;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by ivy on 11/8/14.
 */
public class AccountManager {
    //TODO: AJAX-valid each input of the fields

    public static Account addAccount(String[] fields) throws SQLException{
        //smtpServer, int smtpPort, String popServer, int popPort, boolean SSL, String userName, String password, String emailAddress
        Account newaccount = new Account();
        newaccount.setEmailAddress(fields[0]);
        newaccount.setSmtpServer(fields[1]);
        newaccount.setSmtpPort(fields[2]);
        newaccount.setPopServer(fields[3]);
        newaccount.setPopPort(fields[4]);
        newaccount.setEncryption(fields[5]);
        newaccount.setUserName(fields[6]);
        newaccount.setPassword(fields[7]);
        newaccount.setLocalUser(fields[8]);

        String clause = "INSERT INTO accounts VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement query = DBConnection.db.prepareStatement(clause);
        query.setString(1, fields[0]);
        query.setString(2, fields[1]);
        query.setString(3, fields[2]);
        query.setString(4, fields[3]);
        query.setString(5, fields[4]);
        query.setString(6, fields[5]);
        query.setString(7, fields[6]);
        query.setString(8, fields[7]);
        query.setString(9, fields[8]);

        query.executeUpdate();
        return newaccount;
    }
    //TODO: UPDATE in sqlite
    public static Account updateAccount(){return null;}
    String clause = "SELECT * FROM accounts VALUES WHERE ";
//    PreparedStatement query = DBConnection.db.prepareStatement(clause);
    //get account by localusername, we have one account per use for now
    public static Account getUserAccount(String username) throws SQLException{



        return null;
    }

}
