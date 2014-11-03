package shuai.webmail.managers;

import shuai.webmail.DBService.DBConnection;
import shuai.webmail.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserManager {
    private static String table = "users";


    //Check if username exists in the database
    public static boolean isUser(String name) throws SQLException {
        String clause = "SELECT * FROM users WHERE username= ?";
        PreparedStatement query = DBConnection.db.prepareStatement(clause);
        query.setString(1, name);
        ResultSet result = query.executeQuery();
        return result.next();
    }

    //Check if password matches the username
    public static boolean isPWCorrect(String name, String password) throws SQLException {
        String clause = "SELECT * FROM users WHERE username= ?";
        PreparedStatement query = DBConnection.db.prepareStatement(clause);
        query.setString(1, name);
        ResultSet result = query.executeQuery();
        if(result.next()){
            if(password.equals(result.getString("password"))) return true;
            else return false;
        }else{
            // error message: not a valid username, remain on the log in page
            return false;
        }
    }

    //Register a new user
    public static  boolean addUser(String name, String password) throws SQLException{
        String clause = "INSERT INTO users(username, password) VALUES(?, ?)";
        PreparedStatement query = DBConnection.db.prepareStatement(clause);
        query.setString(1, name);
        query.setString(2, password);
        int result = query.executeUpdate();
        if(result == 1){
            return true;
        }else{
            // error message: registration failed, try again.
            return false;
        }

    }

    //Check userinfo by username
    public static User checkUserInfo(String name) throws SQLException{
        String clause = "SELECT * FROM users WHERE username= ?";
        PreparedStatement query = DBConnection.db.prepareStatement(clause);
        query.setString(1, name);
        ResultSet result = query.executeQuery();

        User checkuser= new User();
        checkuser.id = result.getInt("id");
        checkuser.name = result.getString("username");
        checkuser.password = result.getString("password");

        System.out.println ("id = "+checkuser.id);
        System.out.println ("id = "+checkuser.name);
        System.out.println ("id = "+checkuser.password);
        return checkuser;
    }






}
