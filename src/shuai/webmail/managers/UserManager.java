package shuai.webmail.managers;

import shuai.webmail.db_services.DBConnection;
import shuai.webmail.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ivy on 10/27/14.
 */
public class UserManager {

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
    public static  User addUser(String name, String password) throws SQLException{
        String clause = "INSERT INTO users(username, password) VALUES(?, ?)";
        PreparedStatement query = DBConnection.db.prepareStatement(clause);
        query.setString(1, name);
        query.setString(2, password);
        int result = query.executeUpdate();
        if(result == 1){
            return checkUserInfo(name);
        }else{
            // error message: registration failed, try again.
            return null;
        }

    }

    //Check userinfo by username
    public static User checkUserInfo(String name) throws SQLException{
        String clause = "SELECT * FROM users WHERE username= ?";
        PreparedStatement query = DBConnection.db.prepareStatement(clause);
        query.setString(1, name);
        ResultSet result = query.executeQuery();

        User checkuser= new User();
        checkuser.setId(result.getInt("id"));
        checkuser.setName(result.getString("username"));
        checkuser.setPassword(result.getString("password"));

        /*
        System.out.println ("id = "+checkuser.getId());
        System.out.println ("id = "+checkuser.getName());
        System.out.println ("id = "+checkuser.getPassword());
        */
        return checkuser;
    }






}
