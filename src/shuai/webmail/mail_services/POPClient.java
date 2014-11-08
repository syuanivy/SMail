package shuai.webmail.mail_services;

import shuai.webmail.entities.Account;
import shuai.webmail.entities.Email;
import sun.misc.BASE64Encoder;

import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by ivy on 11/5/14.
 */
public class POPClient {
    private Socket socket;

    private String popServer;
    private int port;
    private boolean SSL;
    private String userName;
    private String password;
    private String emailAddress;

    private BufferedReader bufferedReader;
    private DataOutputStream outputStream;
    private String text;

    public POPClient(Account account) throws IOException {
        this.popServer = account.getPopServer();
        this.port = account.getPopPort();
        this.userName = account.getUserName();
        this.password = account.getPassword();
        this.emailAddress = account.getEmailAddress();
        this.SSL = account.isEncryption();
        //openssl s_client -connect mail.example.com:995
        if(SSL) socket =(SSLSocketFactory.getDefault()).createSocket(this.popServer, port);
        //telnet pop.163.com 110
        else socket = new Socket(this.popServer, port);
        this.text = "";

    }

    public void retrieveEmail() {
        try {

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream = new DataOutputStream(socket.getOutputStream());
            Thread job = new Thread(new Runnable(){
                public void run(){
                    try {
                        String msgFromServer;
                        while ((msgFromServer = (bufferedReader.readLine())) != null) {
                            text = text.concat(msgFromServer+"\r\n");
                            System.out.println(" POP server: " + msgFromServer);

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
           // send username
            System.out.println(" Client: USER "+this.userName);
            sendData("USER "+this.userName);
            Thread.sleep(1000);

            // send password
            System.out.println(" Client: PASS "+this.password);
            sendData("PASS "+this.password);
            Thread.sleep(1000);

            // send status request
            System.out.println("Client: STAT");
            sendData("STAT");
            Thread.sleep(1000);
            // send retrieve email command, the 1st email
            System.out.println("Client: RETR "+1);
            sendData("RETR "+1);
            Thread.sleep(1000);
            //Quit
            System.out.println("Client: QUIT");
            sendData("QUIT");




            // Printout the incoming email
            System.out.println("text = "+text);


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
