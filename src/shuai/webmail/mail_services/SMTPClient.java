package shuai.webmail.mail_services;

import shuai.webmail.entities.Account;
import shuai.webmail.entities.Email;
import sun.misc.BASE64Encoder;

import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;

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

    public SMTPClient(Account account) throws IOException{
        this.smtpServer = account.getSmtpServer();
        this.port = account.getSmtpPort();
        this.userName = account.getUserName();
        this.password = account.getPassword();
        this.emailAddress = account.getEmailAddress();
        this.SSL = account.isEncryption();
        if(SSL) socket =(SSLSocketFactory.getDefault()).createSocket(this.smtpServer, port);
        else socket = new Socket(this.smtpServer, port);

    }

    public void sendEmail(Email email) {
        try {

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream = new DataOutputStream(socket.getOutputStream());
            Thread job = new Thread(new Runnable(){
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

            Thread.sleep(1000);
            System.out.println("HELO "+this.smtpServer);
            sendData("HELO "+this.smtpServer);
            Thread.sleep(1000);

            // AUTH LOGIN
            System.out.println(" Client: AUTH LOGIN");
            sendData("AUTH LOGIN");
            Thread.sleep(1000);

            // send user name
            System.out.println("Client: "+new BASE64Encoder().encode(this.userName.getBytes()));
            sendData(new BASE64Encoder().encode(this.userName.getBytes()));
            Thread.sleep(1000);

            // send password
            System.out.println("Client: "+new BASE64Encoder().encode(this.password.getBytes()));
            sendData(new BASE64Encoder().encode(this.password.getBytes()));
            Thread.sleep(1000);

            //FROM:<sender>
            System.out.println("Client: MAIL FROM:<" + this.emailAddress + ">");
            sendData("MAIL FROM:<" + this.emailAddress + ">");
            Thread.sleep(1000);

            // TO:<recipient>
            System.out.println(" Client: RCPT TO:<"+email.getRecipient()+">");
            sendData("RCPT TO:<" + email.getRecipient() + ">");
            Thread.sleep(1000);

            System.out.println(" Client: DATA");
            sendData("DATA");
            Thread.sleep(1000);
            // send email header, ending with a blank line
            sendData("Subject: "+email.getSubject()+"\r\n");
            Thread.sleep(1000);
            /*//**//*//*TODO: add other headers
            C: From: "Bob Example" <bob@example.org>
            C: To: "Alice Example" <alice@example.com>
            C: Cc: theboss@example.com
            C: Date: Tue, 15 January 2008 16:02:43 -0500
            */

            // send plain text
            sendData(email.getBody());
            Thread.sleep(1000);
            //end signal
            System.out.println(" Client:. ");
            sendData(".");
            Thread.sleep(1000);
            // QUIT
            System.out.println(" Client: QUIT");
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
