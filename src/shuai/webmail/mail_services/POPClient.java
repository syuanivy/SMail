package shuai.webmail.mail_services;

import com.sun.jndi.cosnaming.IiopUrl;
import org.eclipse.jetty.server.session.JDBCSessionManager;
import shuai.webmail.entities.Account;
import shuai.webmail.entities.Incoming;
import shuai.webmail.managers.EmailManager;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by ivy on 11/5/14.
 */
public class POPClient {
    private Socket socket;
    private BufferedReader in;
    private DataOutputStream out;
    private Account account;


    public POPClient(Account account) throws InterruptedException,IOException {
        this.account = account;
        this.socket = connectPOP(account);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new DataOutputStream(socket.getOutputStream());
        verifyOK(in.readLine());
        logIn();
    }

    public Socket connectPOP(Account account){
        if(account.isEncryption()==1)
            try {
                socket =(SSLSocketFactory.getDefault()).createSocket(account.getPopServer(), account.getPopPort());
            } catch (Exception e) {
                e.printStackTrace();
            }
        else {
            try{
                socket = new Socket(account.getPopServer(),account.getPopPort());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return socket;
    }

    private void logIn() throws InterruptedException,IOException{
        // send username
        System.out.println(" Client: USER "+account.getUserName());
        sendData("USER "+account.getUserName());
        String msgLine = in.readLine();
        System.out.println(msgLine);
        verifyOK(msgLine);

        // send password
        System.out.println(" Client: PASS "+account.getPassword());
        sendData("PASS "+account.getPassword());
        msgLine = in.readLine();
        System.out.println(msgLine);
        verifyOK(msgLine);
    }


    private void sendData(String data) throws InterruptedException {
        try {
            out.writeBytes(data + "\r\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void verifyOK(String msgLine) throws IOException{
        if(!msgLine.startsWith("+")){
            throw new IOException(msgLine);
        }
    }

    public ArrayList<Incoming> retrieveAll() throws IOException, InterruptedException{
        ArrayList<Incoming> newMails = new ArrayList<Incoming>();
        for (Integer id : getIdList().keySet()) {
            newMails.add(retrieveOneMail(id));
        }
        return newMails;
    }

    private Map<Integer, String> getIdList() throws IOException,InterruptedException {
        sendData("UIDL");
        String msgLine = in.readLine();
        System.out.println(msgLine);
        verifyOK(msgLine);
        Map<Integer, String> idList = new HashMap<Integer, String>();
        while (!(msgLine = in.readLine()).equalsIgnoreCase(".")) {
            System.out.println(msgLine);
            String[] ids = msgLine.split(" ");
            idList.put(Integer.parseInt(ids[0]),ids[1]);
        }
        return idList;
    }

    public Incoming retrieveOneMail(Integer id) throws IOException, InterruptedException{
        sendData("RETR " + id);
        String msgLine = in.readLine();
        System.out.println(msgLine);
        verifyOK(msgLine);

        Incoming newMail = new Incoming();
        StringBuffer content = new StringBuffer();
        try {
            //obtain the whole message from the server including the end signal
            while (!(msgLine = in.readLine()).equalsIgnoreCase(".")) {
                System.out.println(msgLine);
                content.append(msgLine + "\n");
            }
            content.append("." + "\n");

            Session session = Session.getDefaultInstance(new Properties());
            InputStream inputStream = new ByteArrayInputStream(content.toString().getBytes());
            MimeMessage message = new MimeMessage(session, inputStream);
            newMail.setTime(message.getSentDate().toString());
            newMail.setSubject(message.getSubject());
            Address[] senders = message.getFrom();
            String senderString = "";
            for (Address sender : senders) {
                senderString.concat("," + sender.toString());
            }
            Address[] recipients = message.getRecipients(Message.RecipientType.TO);
            String recipientString = "";
            for (Address recipient : recipients) {
                recipientString.concat("," + recipient.toString());
            }
            newMail.setSender(senderString);
            newMail.setRecipient(recipientString);
            String contentType = message.getContentType();

            String messageContent = "";
            String attachment = "";

            if (contentType.contains("multipart")) {
                // content may contain attachments
                Multipart multiPart = (Multipart) message.getContent();
                int numberOfParts = multiPart.getCount();
                for (int Count = 0; Count < numberOfParts; Count++) {
                    MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(Count);
                    if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                        // this part is attachment
                        String fileName = part.getFileName();
                        attachment += fileName + ", ";
                        part.saveFile("" + File.separator + fileName);
                    } else {
                        // this part may be the message content
                        messageContent = part.getContent().toString();
                    }
                }

                if (attachment.length() > 1) {
                    attachment = attachment.substring(0, attachment.length() - 2);
                }
            } else if (contentType.contains("text/plain")
                    || contentType.contains("text/html")) {
                Object body = message.getContent();
                if (body != null) {
                    messageContent = body.toString();
                }
            }

            newMail.setBody(messageContent);
            return newMail;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        return null;

    }



}
