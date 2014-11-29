package shuai.webmail.managers;
import static shuai.webmail.db_services.DBConnection.*;
import shuai.webmail.entities.User;
import sun.misc.BASE64Encoder;
import java.io.IOException;
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
        PreparedStatement query = db.prepareStatement(clause);
        query.setString(1, name);
        ResultSet result = query.executeQuery();
        return result;
    }

    //Check if password matches the username, the username has been confirmed to be existing user
    public static boolean isPWCorrect(String pwInput, String pwCoded) throws SQLException, IOException {
        if(new BASE64Encoder().encode(pwInput.getBytes()).equals(pwCoded)) return true;
        else return false;
    }

    //Register a new user, the username has been confirmed to be non-existing//
    public static  User addUser(String[] userFields) throws SQLException,IOException{
        String clause = "INSERT INTO users(username, password) VALUES(?, ?)";
        PreparedStatement query = db.prepareStatement(clause);
        query.setString(1, userFields[0]);//String name
        query.setString(2, new BASE64Encoder().encode(userFields[1].getBytes()));// encoded password
        int result = query.executeUpdate();
        if(result == 1){
            return checkUserInfo(userFields[0]);
        }else{
            return null;
        }
    }

    //Check userinfo by username
    public static User checkUserInfo(String name) throws SQLException, IOException{
        ResultSet result = findUser(name);
        if (!result.next()) return null; //not a registered user
        User checkuser= new User();
        checkuser.setId(result.getInt("id"));
        checkuser.setName(result.getString("username"));
        checkuser.setPassword(result.getString("password"));
        return checkuser;
    }

    //change password, currently do not support other modifications of user information
    public static void changePassword(User user, String newPW) throws SQLException, IOException{
        String clause = "UPDATE users SET password = ? WHERE username = ?";
        PreparedStatement query = db.prepareStatement(clause);
        query.setString(1, new BASE64Encoder().encode(newPW.getBytes()));//String name
        query.setString(2, user.getName());// encoded password
        query.executeUpdate();
    }





}
