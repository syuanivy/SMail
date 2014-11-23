package shuai.webmail.managers;

import shuai.webmail.db_services.DBConnection;
import shuai.webmail.entities.Account;
import shuai.webmail.entities.User;
import sun.misc.BASE64Encoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        newaccount.setSmtpPort(Integer.parseInt(fields[2]));
        newaccount.setPopServer(fields[3]);
        newaccount.setPopPort(Integer.parseInt(fields[4]));
        newaccount.setEncryption(Integer.parseInt(fields[5]));
        newaccount.setUserName(fields[6]);
        newaccount.setPassword(new BASE64Encoder().encode(fields[7].getBytes()));
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
        query.setString(8, new BASE64Encoder().encode(fields[7].getBytes()));
        query.setString(9, fields[8]);

        query.executeUpdate();
        return newaccount;
    }
    //TODO: UPDATE in sqlite
    public static Account updateAccount(){return null;}

    public static Account getUserAccount(String localUser) throws SQLException{
        String clause = "SELECT * FROM accounts WHERE my_user= ?";
        PreparedStatement query = DBConnection.db.prepareStatement(clause);
        query.setString(1, localUser);
        ResultSet result = query.executeQuery();

        Account account= new Account();

        account.setEmailAddress(result.getString("email_address"));
        account.setSmtpServer(result.getString("smtp"));
        account.setSmtpPort(result.getInt("smtp_port"));
        account.setPopServer(result.getString("pop"));
        account.setPopPort(result.getInt("pop_port"));
        account.setEncryption(result.getInt("ssl"));
        account.setUserName(result.getString("username"));
        account.setPassword(result.getString("password"));
        account.setLocalUser(result.getString("my_user"));

        return account;

    }


}
