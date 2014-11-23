package shuai.webmail.mail_services;

import shuai.webmail.entities.Account;
import shuai.webmail.entities.Outgoing;
import shuai.webmail.managers.EmailManager;
import sun.misc.BASE64Encoder;

import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Created by ivy on 11/4/14.
 */
public class SMTPClient {
    private Socket socket;
    private BufferedReader in;
    private DataOutputStream out;
    private Account account;
    private Outgoing email;

    public SMTPClient(Account account, Outgoing email) throws IOException,InterruptedException{
        this.account = account;
        this.email = email;
        this.socket = connectSMTP(account);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new DataOutputStream(socket.getOutputStream());
        verify(in.readLine(), "220");
        handShake();
        login();



    }
    public Socket connectSMTP(Account account) throws IOException{
        if(account.isEncryption()==1) socket =(SSLSocketFactory.getDefault()).createSocket(account.getSmtpServer(), account.getSmtpPort());
        else socket = new Socket(account.getSmtpServer(), account.getSmtpPort());
        return socket;
    }

    public boolean verify(String msgLine, String expectedResponse) {
        if(!msgLine.contains(expectedResponse)){
            return false;
        }
        return true;
    }

    public void send(Outgoing email) throws InterruptedException, IOException,SQLException{
        sendFromAccount();
        sendToAddresses();
        sendEmail();
        quitAndClose();
    }

    private void quitAndClose() throws InterruptedException, IOException {
        sendData("QUIT");
        verify(in.readLine(),"221");
        try{
            in.close();
            out.close();
            socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void sendEmail() throws SQLException, InterruptedException,IOException {
        sendData("DATA");
        verify(in.readLine(), "354");
        // send email header, ending with a blank line
        sendData("From: "+email.getSender());
        sendData("To: "+email.getRecipient());
        sendData("Subject: "+ email.getSubject()+"\r\n");
        sendData(email.getBody());
        sendData(".");
        boolean sent = verify(in.readLine(), "250");
        if(sent==true) {
            email.setSent(1);
            EmailManager.addOutgoing(email);
        }
    }

    private void sendToAddresses() throws InterruptedException, IOException {
        sendData("RCPT TO:<" + email.getRecipient() + ">");
        verify(in.readLine(), "250");
    }

    private void sendFromAccount() throws InterruptedException, IOException {
        sendData("MAIL FROM:<" + account.getEmailAddress() + ">");
        verify(in.readLine(), "250");
    }

    private void login() throws InterruptedException, IOException {
        sendData("AUTH LOGIN");
        verify(in.readLine(), "334");
        // send user name
        sendData(new BASE64Encoder().encode(account.getUserName().getBytes()));//encode username
        verify(in.readLine(), "334");
        // send password
        sendData(account.getPassword());//already encoded in db
        verify(in.readLine(), "235");
    }

    private void handShake() throws InterruptedException, IOException {
        sendData("HELO "+account.getSmtpServer());
        verify(in.readLine(),"250");
    }


    private void sendData(String data) throws InterruptedException {
        try {
            out.writeBytes(data + "\r\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
