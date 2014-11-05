package shuai.webmail.mail_services;

import shuai.webmail.entities.Email;
import shuai.webmail.entities.SMTPAccount;
import sun.misc.BASE64Encoder;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by ivy on 11/4/14.
 */
public class SMTPClient {
    private Socket socket;

    private String smtpServer;
    private int port;
    private boolean SSL;
    private String userName;
    private String password;
    private String emailAddress;

    private BufferedReader bufferedReader;
    private DataOutputStream outputStream;

    public SMTPClient(SMTPAccount account) throws IOException{
        this.smtpServer = account.getSmtpServer();
        this.port = account.getPort();
        this.userName = account.getUserName();
        this.password = account.getPassword();
        this.emailAddress = account.getEmailAddress();
        this.SSL = account.isEncryption();
        if(SSL) socket =(SSLSocketFactory.getDefault()).createSocket(this.smtpServer, port);
        else socket = new Socket();

    }

    public void sendEMail(Email email) {
        try {

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream = new DataOutputStream(socket.getOutputStream());
            Thread job = new Thread();
            job = new Thread(new Runnable(){
                                 public void run(){
                                     try {
                                         String msgFromServer;
                                         while ((msgFromServer = (bufferedReader.readLine())) != null) {
                                         System.out.println(" SMTP server: " + msgFromServer);
                                         }
                                     }catch (Exception e){
                                         System.out.println(" The reader has a problem.");
                                         try {
                                             bufferedReader.close();
                                             outputStream.close();
                                             socket.close();
                                         } catch (IOException e1) {
                                              e1.printStackTrace();
                                         }
                                     }
                                 }
                                });
            job.start();
            System.out.println(" Client: HELO  serverName <CRLF>");
            sendData("HELO smtp.gmail.com");

            // AUTH LOGIN <CRLF>
            System.out.println(" Client: AUTH LOGIN <CRLF>");
            sendData("AUTH LOGIN");

            // send user name
            System.out.println(" Client: user name <CRLF>");
            sendData(new BASE64Encoder().encode(this.userName.getBytes()));

            // send password
            System.out.println(" Client: password <CRLF>");
            sendData(new BASE64Encoder().encode(this.password.getBytes()));

            //FROM:<sender> <CRLF>
            System.out.println(" Client: MAIL <SP> FROM:<reverse-path> <CRLF>");
            sendData("MAIL FROM:<" + this.emailAddress + ">");

            // TO:<recipient> <CRLF>
            System.out.println(" Client: RCPT <SP> TO:<forward-path> <CRLF>");
            sendData("RCPT TO:<" + email.getRecipient() + ">");


            System.out.println(" Client: DATA <CRLF>");
            sendData("DATA");

            // send email header
            sendData(email.getSubject());

            // send plain text email content now!!
            sendData(email.getBody());

            //end signal
            System.out.println(" Client: <CRLF> . <CRLF>");
            sendData("\r\n.");

            // QUIT
            System.out.println(" Client:: QUIT\r\n");
            sendData("QUIT");

        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    private void sendData(String data) throws InterruptedException {
        try {
            outputStream.writeBytes(data + "\r\n");
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
