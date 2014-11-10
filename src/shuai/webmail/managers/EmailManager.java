package shuai.webmail.managers;

import shuai.webmail.db_services.DBConnection;
import shuai.webmail.entities.Incoming;
import shuai.webmail.entities.Outgoing;

import java.sql.PreparedStatement;

/**
 * Created by ivy on 10/27/14.
 */
public class EmailManager {
/*  String clause = "SELECT * FROM users WHERE username= ?";
    PreparedStatement query = DBConnection.db.prepareStatement(clause);
    query.setString(1, name);
    ResultSet result = query.executeQuery();
    return result;
*/
/*
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
*/



    public static void addOutgoing(Outgoing email){

    }

    public static void addIncoming(Incoming email){

    }

}
