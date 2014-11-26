package shuai.webmail.managers;


import shuai.webmail.entities.Account;
import shuai.webmail.entities.Email;
import shuai.webmail.entities.Incoming;
import shuai.webmail.entities.Outgoing;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import static shuai.webmail.db_services.DBConnection.*;

/**
 * Created by ivy on 10/27/14.
 */
public class EmailManager {

    public static void addOutgoing(Outgoing email)throws SQLException{
        String clause = "INSERT INTO outgoing(sender, recipient, subject, body, label) VALUES(?, ?, ?, ? ,?)";
        PreparedStatement query = db.prepareStatement(clause);
        query.setString(1, email.getSender());
        query.setString(2, email.getRecipient());
        query.setString(3, email.getSubject());
        query.setString(4, email.getBody());
        query.setString(5, String.valueOf(email.isSent()));
        query.executeUpdate();
    }

    public static void addIncoming(Incoming email) throws SQLException{
        String clause = "INSERT OR IGNORE INTO incoming(id, sender, recipient, subject, body) VALUES(?, ?, ?, ? ,?)";
        PreparedStatement query = db.prepareStatement(clause);
        query.setString(1, email.getID());
        query.setString(2, email.getSender());
        query.setString(3, email.getRecipient());
        query.setString(4, email.getSubject());
        query.setString(5, email.getBody());
        query.executeUpdate();
    }

    public static Email findEmail(String id, String foldername) throws SQLException{
        Email email = new Email();
        String clause = new String();
        if(foldername.equals("inbox")) clause = "SELECT id, sender,recipient, subject, body, time,label FROM incoming WHERE id = ?";
        if(foldername.equals("sent")) clause = "SELECT id, sender, recipient, subject, body, time,label FROM outgoing WHERE id = ?";
        if(foldername.equals("draft")) clause = "SELECT id, sender, recipient, subject, body, time,label FROM outgoing WHERE id = ?";

        PreparedStatement query = db.prepareStatement(clause);
        query.setString(1, id);
        ResultSet result = query.executeQuery();
        while (result.next()){
            email.setID(result.getString(1));
            email.setSender(result.getString(2));
            email.setRecipient(result.getString(3));
            email.setSubject(result.getString(4));
            email.setBody(result.getString(5));
            email.setTime(result.getString(6));
            email.setLabel(result.getInt(7));
            result.close();
            return email;
        }

        return null;
    }
public static String findFolder(String label){
    String foldername = new String();
    if(label.equals("0")) foldername = "inbox";
    if(label.equals("1")) foldername = "sent";
    if(label.equals("2")) foldername = "draft";
    if(label.equals("3")) foldername = "trash";
    return foldername;
}



/*
    if(s.startsWith("Date:")){
        time = s.substring(s.indexOf(":")+1);
        Date date = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        time = dateFormat.format(date);
    }*/


    public static ArrayList<Email> mailList(Account account, String foldername) throws SQLException{
        String clause = new String();
        PreparedStatement query = new PreparedStatement() {
            @Override
            public ResultSet executeQuery() throws SQLException {
                return null;
            }

            @Override
            public int executeUpdate() throws SQLException {
                return 0;
            }

            @Override
            public void setNull(int i, int i2) throws SQLException {

            }

            @Override
            public void setBoolean(int i, boolean b) throws SQLException {

            }

            @Override
            public void setByte(int i, byte b) throws SQLException {

            }

            @Override
            public void setShort(int i, short i2) throws SQLException {

            }

            @Override
            public void setInt(int i, int i2) throws SQLException {

            }

            @Override
            public void setLong(int i, long l) throws SQLException {

            }

            @Override
            public void setFloat(int i, float v) throws SQLException {

            }

            @Override
            public void setDouble(int i, double v) throws SQLException {

            }

            @Override
            public void setBigDecimal(int i, BigDecimal bigDecimal) throws SQLException {

            }

            @Override
            public void setString(int i, String s) throws SQLException {

            }

            @Override
            public void setBytes(int i, byte[] bytes) throws SQLException {

            }

            @Override
            public void setDate(int i, Date date) throws SQLException {

            }

            @Override
            public void setTime(int i, Time time) throws SQLException {

            }

            @Override
            public void setTimestamp(int i, Timestamp timestamp) throws SQLException {

            }

            @Override
            public void setAsciiStream(int i, InputStream inputStream, int i2) throws SQLException {

            }

            @Override
            public void setUnicodeStream(int i, InputStream inputStream, int i2) throws SQLException {

            }

            @Override
            public void setBinaryStream(int i, InputStream inputStream, int i2) throws SQLException {

            }

            @Override
            public void clearParameters() throws SQLException {

            }

            @Override
            public void setObject(int i, Object o, int i2) throws SQLException {

            }

            @Override
            public void setObject(int i, Object o) throws SQLException {

            }

            @Override
            public boolean execute() throws SQLException {
                return false;
            }

            @Override
            public void addBatch() throws SQLException {

            }

            @Override
            public void setCharacterStream(int i, Reader reader, int i2) throws SQLException {

            }

            @Override
            public void setRef(int i, Ref ref) throws SQLException {

            }

            @Override
            public void setBlob(int i, Blob blob) throws SQLException {

            }

            @Override
            public void setClob(int i, Clob clob) throws SQLException {

            }

            @Override
            public void setArray(int i, Array array) throws SQLException {

            }

            @Override
            public ResultSetMetaData getMetaData() throws SQLException {
                return null;
            }

            @Override
            public void setDate(int i, Date date, Calendar calendar) throws SQLException {

            }

            @Override
            public void setTime(int i, Time time, Calendar calendar) throws SQLException {

            }

            @Override
            public void setTimestamp(int i, Timestamp timestamp, Calendar calendar) throws SQLException {

            }

            @Override
            public void setNull(int i, int i2, String s) throws SQLException {

            }

            @Override
            public void setURL(int i, URL url) throws SQLException {

            }

            @Override
            public ParameterMetaData getParameterMetaData() throws SQLException {
                return null;
            }

            @Override
            public void setRowId(int i, RowId rowId) throws SQLException {

            }

            @Override
            public void setNString(int i, String s) throws SQLException {

            }

            @Override
            public void setNCharacterStream(int i, Reader reader, long l) throws SQLException {

            }

            @Override
            public void setNClob(int i, NClob nClob) throws SQLException {

            }

            @Override
            public void setClob(int i, Reader reader, long l) throws SQLException {

            }

            @Override
            public void setBlob(int i, InputStream inputStream, long l) throws SQLException {

            }

            @Override
            public void setNClob(int i, Reader reader, long l) throws SQLException {

            }

            @Override
            public void setSQLXML(int i, SQLXML sqlxml) throws SQLException {

            }

            @Override
            public void setObject(int i, Object o, int i2, int i3) throws SQLException {

            }

            @Override
            public void setAsciiStream(int i, InputStream inputStream, long l) throws SQLException {

            }

            @Override
            public void setBinaryStream(int i, InputStream inputStream, long l) throws SQLException {

            }

            @Override
            public void setCharacterStream(int i, Reader reader, long l) throws SQLException {

            }

            @Override
            public void setAsciiStream(int i, InputStream inputStream) throws SQLException {

            }

            @Override
            public void setBinaryStream(int i, InputStream inputStream) throws SQLException {

            }

            @Override
            public void setCharacterStream(int i, Reader reader) throws SQLException {

            }

            @Override
            public void setNCharacterStream(int i, Reader reader) throws SQLException {

            }

            @Override
            public void setClob(int i, Reader reader) throws SQLException {

            }

            @Override
            public void setBlob(int i, InputStream inputStream) throws SQLException {

            }

            @Override
            public void setNClob(int i, Reader reader) throws SQLException {

            }

            @Override
            public ResultSet executeQuery(String s) throws SQLException {
                return null;
            }

            @Override
            public int executeUpdate(String s) throws SQLException {
                return 0;
            }

            @Override
            public void close() throws SQLException {

            }

            @Override
            public int getMaxFieldSize() throws SQLException {
                return 0;
            }

            @Override
            public void setMaxFieldSize(int i) throws SQLException {

            }

            @Override
            public int getMaxRows() throws SQLException {
                return 0;
            }

            @Override
            public void setMaxRows(int i) throws SQLException {

            }

            @Override
            public void setEscapeProcessing(boolean b) throws SQLException {

            }

            @Override
            public int getQueryTimeout() throws SQLException {
                return 0;
            }

            @Override
            public void setQueryTimeout(int i) throws SQLException {

            }

            @Override
            public void cancel() throws SQLException {

            }

            @Override
            public SQLWarning getWarnings() throws SQLException {
                return null;
            }

            @Override
            public void clearWarnings() throws SQLException {

            }

            @Override
            public void setCursorName(String s) throws SQLException {

            }

            @Override
            public boolean execute(String s) throws SQLException {
                return false;
            }

            @Override
            public ResultSet getResultSet() throws SQLException {
                return null;
            }

            @Override
            public int getUpdateCount() throws SQLException {
                return 0;
            }

            @Override
            public boolean getMoreResults() throws SQLException {
                return false;
            }

            @Override
            public void setFetchDirection(int i) throws SQLException {

            }

            @Override
            public int getFetchDirection() throws SQLException {
                return 0;
            }

            @Override
            public void setFetchSize(int i) throws SQLException {

            }

            @Override
            public int getFetchSize() throws SQLException {
                return 0;
            }

            @Override
            public int getResultSetConcurrency() throws SQLException {
                return 0;
            }

            @Override
            public int getResultSetType() throws SQLException {
                return 0;
            }

            @Override
            public void addBatch(String s) throws SQLException {

            }

            @Override
            public void clearBatch() throws SQLException {

            }

            @Override
            public int[] executeBatch() throws SQLException {
                return new int[0];
            }

            @Override
            public Connection getConnection() throws SQLException {
                return null;
            }

            @Override
            public boolean getMoreResults(int i) throws SQLException {
                return false;
            }

            @Override
            public ResultSet getGeneratedKeys() throws SQLException {
                return null;
            }

            @Override
            public int executeUpdate(String s, int i) throws SQLException {
                return 0;
            }

            @Override
            public int executeUpdate(String s, int[] ints) throws SQLException {
                return 0;
            }

            @Override
            public int executeUpdate(String s, String[] strings) throws SQLException {
                return 0;
            }

            @Override
            public boolean execute(String s, int i) throws SQLException {
                return false;
            }

            @Override
            public boolean execute(String s, int[] ints) throws SQLException {
                return false;
            }

            @Override
            public boolean execute(String s, String[] strings) throws SQLException {
                return false;
            }

            @Override
            public int getResultSetHoldability() throws SQLException {
                return 0;
            }

            @Override
            public boolean isClosed() throws SQLException {
                return false;
            }

            @Override
            public void setPoolable(boolean b) throws SQLException {

            }

            @Override
            public boolean isPoolable() throws SQLException {
                return false;
            }

            @Override
            public void closeOnCompletion() throws SQLException {

            }

            @Override
            public boolean isCloseOnCompletion() throws SQLException {
                return false;
            }

            @Override
            public <T> T unwrap(Class<T> tClass) throws SQLException {
                return null;
            }

            @Override
            public boolean isWrapperFor(Class<?> aClass) throws SQLException {
                return false;
            }
        };
/*
        switch (foldername) {
            case "inbox":
                clause = "SELECT id, sender, subject, body, time FROM incoming WHERE recipient = ? ORDER BY time DESC";
            case "sent":
                clause = "SELECT id, sender, subject, body, time FROM outgoing WHERE recipient = ? ORDER BY time DESC";
        }*/
        if(foldername.equals("inbox")) {
            clause = "SELECT id, sender, subject, body, time, label FROM incoming WHERE recipient = ? ORDER BY time DESC";
            query = db.prepareStatement(clause);
            query.setString(1, account.getEmailAddress());
        }
        if(foldername.equals("sent")) {
            clause = "SELECT id, sender, subject, body, time, label FROM outgoing WHERE sender = ? AND label = 1 ORDER BY time DESC";
            query = db.prepareStatement(clause);
            query.setString(1, account.getEmailAddress());
        }
        if(foldername.equals("draft")){
            clause = "SELECT id, sender, subject, body, time,label FROM outgoing WHERE label=2";
            query = db.prepareStatement(clause);

        }
        ResultSet result = query.executeQuery();
        ArrayList<Email> mailList = new ArrayList<Email>();
        while (result.next()){
            Email mail = new Email();
            mail.setID(result.getString(1));
            mail.setSender(result.getString(2));
            mail.setSubject(result.getString(3));
            mail.setBody(result.getString(4));
            mail.setTime(result.getString(5));
            mail.setLabel(result.getInt(6));
            mailList.add(mail);
        }
        result.close();
        return mailList;

    }
}
