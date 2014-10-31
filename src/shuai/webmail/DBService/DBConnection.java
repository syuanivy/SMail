package shuai.webmail.DBService;

import java.sql.*;


public class DBConnection {
    public static final String dbFile = "/home/ivy/Documents/cs601/project4/syuanivy-webmail/resources/shuai/webmail/db/webmail.db";
    public static Connection db = null;


    public static void getDBConnection(){
        try {
            Class.forName("org.sqlite.JDBC"); // force load of driver
            db = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
