package shuai.webmail.DBService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ivy on 10/30/14.
 */
public class Select {
    public String clause;
    public PreparedStatement query;
    public ResultSet result;

    //SELECT * FROM table
    public Select(String table) throws SQLException{
        this.clause = "SELECT * FROM" + " " + table;
        this.query = DBConnection.db.prepareStatement(clause);
        this.result = query.executeQuery();

    }

    //SELECT * FROM table WHERE columnName = string
    public Select(String table, String column, String value) throws SQLException{
        this.clause = "SELECT * FROM " + table + " WHERE " + column + "='" + value + "'";
        this.query = DBConnection.db.prepareStatement(clause);
        this.result = query.executeQuery();

    }

    //SELECT a column FROM table
    public Select(String table, String column) throws SQLException{
        this.clause = "SELECT " + column + " FROM" + " " + table;
        this.query = DBConnection.db.prepareStatement(clause);
        this.result = query.executeQuery();

    }

    //SELECT column1, column2... FROM table
    public Select(String table, String[] columns) throws SQLException{
        int n = columns.length;
        String temp = columns[0];
        for (int i = 1; i < n; i++){
            temp = temp.concat(", "+columns[i]);
        }
        this.clause = "SELECT " + temp + " FROM" + " " + table;
        this.query = DBConnection.db.prepareStatement(clause);
        this.result = query.executeQuery();
    }

}
