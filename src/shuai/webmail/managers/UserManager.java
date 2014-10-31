package shuai.webmail.managers;

import shuai.webmail.DBService.Select;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserManager {
    private static String table = "users";


    //Check if username exists in the database
    public static boolean isUser(String name) throws SQLException {
        Select userinfo = new Select(table, "username", name);
        return userinfo.result.next();
    }

    //Check if password matches the username
    public static boolean isPWCorrect(String name, String password) throws SQLException {
        Select userinfo = new Select(table, "username", name);
        if(userinfo.result.next()){
            if(password.equals(userinfo.result.getString("password"))) return true;
            else return false;
        }else{
            // error message: not a valid username, remain on the log in page
            return false;
        }
    }








}
