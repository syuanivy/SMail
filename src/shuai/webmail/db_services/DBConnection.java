package shuai.webmail.db_services;

import java.sql.*;


public class DBConnection {
    public static final String dbFile = "src/shuai/webmail/db_services/db/webmail_dev.db";
    public static Connection db = null;


    public static Connection getDBConnection(){
        try {
            Class.forName("org.sqlite.JDBC"); // force load of driver
            db = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
            return db;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void closeDBConnection(Connection db) {
        try {
            if (db != null) {
                db.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
