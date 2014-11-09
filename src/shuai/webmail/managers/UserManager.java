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

    //attempt to find user information in db
    public static ResultSet findUser(String name) throws SQLException {
        String clause = "SELECT * FROM users WHERE username= ?";
        PreparedStatement query = DBConnection.db.prepareStatement(clause);
        query.setString(1, name);
        ResultSet result = query.executeQuery();
        return result;
    }

    //Check if username exists in the database
    //TODO: AJAX
    public static boolean isUser(String name) throws SQLException {
        ResultSet result = findUser(name);
        return result.next();
    }

    //Check if password matches the username, the username has been confirmed to be existing user
    public static boolean isPWCorrect(String name, String password) throws SQLException {
        ResultSet result = findUser(name);
        if(password.equals(result.getString("password"))) return true;
        else return false;
    }

    //Register a new user, the username has been confirmed to be non-existing//
    public static  User addUser(String[] userFields) throws SQLException{
        String clause = "INSERT INTO users(username, password) VALUES(?, ?)";
        PreparedStatement query = DBConnection.db.prepareStatement(clause);
        query.setString(1, userFields[0]);//String name
        query.setString(2, userFields[1]);// String password
        int result = query.executeUpdate();
        if(result == 1){
            return checkUserInfo(userFields[0]);
        }else{
            return null;
        }
    }

    //Check userinfo by username
    public static User checkUserInfo(String name) throws SQLException{
        ResultSet result = findUser(name);
        User checkuser= new User();
        checkuser.setId(result.getInt("id")); //now it is the PRIMARY KEY in the users table
        checkuser.setName(result.getString("username"));
        checkuser.setPassword(result.getString("password"));
        return checkuser;
    }






}
