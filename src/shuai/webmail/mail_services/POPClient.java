package shuai.webmail.mail_services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import shuai.webmail.entities.Account;
import shuai.webmail.entities.Incoming;
import shuai.webmail.managers.EmailManager;
import sun.misc.BASE64Decoder;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimePart;
import javax.net.ssl.SSLSocketFactory;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.*;

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
        login();
    }

    public Socket connectPOP(Account account) throws IOException{
        if(account.isEncryption()==1) socket =(SSLSocketFactory.getDefault()).createSocket(account.getPopServer(), account.getPopPort());
        else  socket = new Socket(account.getPopServer(),account.getPopPort());
        return socket;
    }

    private void login() throws InterruptedException,IOException{
        sendData("USER "+account.getUserName());
        verifyOK(in.readLine());
        sendData("PASS "+new String(new BASE64Decoder().decodeBuffer(account.getPassword()), "UTF-8"));//decoded password
        verifyOK(in.readLine());
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

    public ArrayList<Incoming> retrieveAll() throws IOException, InterruptedException,SQLException{
        ArrayList<Incoming> newMails = new ArrayList<Incoming>();
        Map<Integer, String> idList = getIdList();
        for (Integer id : idList.keySet()) {
            newMails.add(retrieveOneMail(id, idList.get(id)));
        }
        return newMails;
    }

    private Map<Integer, String> getIdList() throws IOException,InterruptedException {
        sendData("UIDL");
        String msgLine = in.readLine();
        verifyOK(msgLine);
        Map<Integer, String> idList = new HashMap<Integer, String>();
        while (!(msgLine = in.readLine()).equalsIgnoreCase(".")) {
            String[] ids = msgLine.split(" ");
            idList.put(Integer.parseInt(ids[0]),ids[1]);
        }
        return idList;
    }

    public Incoming retrieveOneMail(Integer id, String UID) throws IOException, InterruptedException,SQLException{
        sendData("RETR " + id);
        String msgLine = in.readLine();
        verifyOK(msgLine);

        Incoming newMail = new Incoming();
        StringBuffer input = new StringBuffer();
        try {
            //obtain the whole message from the server including the end signal
            while (!(msgLine = in.readLine()).equalsIgnoreCase(".")) {
                input.append(msgLine + "\n");
            }
            input.append("." + "\n");

            Session session = Session.getDefaultInstance(new Properties());
            InputStream inputStream = new ByteArrayInputStream(input.toString().getBytes());
            MimeMessage message = new MimeMessage(session, inputStream);

            setID(newMail,UID);
            setDate(newMail, message);
            setSubject(newMail, message);
            setSender(newMail, message);
            setRecipient(newMail, message);



            String contentType = message.getContentType();
            String[] content = getBodyAttachment(message, contentType);
            String body = content [0];
            String attachment = content[1];

            newMail.setBody(body);
            EmailManager.addIncoming(newMail);
            return newMail;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        return null;

    }

    private void setSubject(Incoming newMail, MimeMessage message) throws MessagingException {
        newMail.setSubject(message.getSubject());
    }

    private void setID(Incoming newMail, String UID) {
        newMail.setID(UID);
    }

    private void setDate(Incoming newMail, MimeMessage message) throws MessagingException {
        Date date = message.getSentDate();
        if(date == null){
            date = message.getReceivedDate();
        }else if(date == null){
            date = new Date();
        }
        newMail.setTime(date.toString());
    }

    private String[] getBodyAttachment(MimeMessage message, String contentType) throws IOException, MessagingException {
        String body = "";
        String attachment = "";

        if(contentType.contains("text/plain")) {
            Object Body = message.getContent();
            if (Body != null) {
                body = Body.toString();
            }
        }else if ( contentType.contains("text/html")) {
            Object Body = message.getContent();
            if (Body != null) {
                body = Body.toString();
                Document html = Jsoup.parse(body);
                html.select("body").tagName("div");
                body = html.html();
            }
        }else{
          //  Multipart parts = (Multipart) message.getContent();



        }
        /*if (contentType.contains("multipart")) {

            int numberOfParts = parts.getCount();
            for (int i = 0; i < numberOfParts; i++) {
                MimeBodyPart part = (MimeBodyPart) parts.getBodyPart(i);
                if(part.isMimeType("multipart*//*")){
                    Multipart multipart = (Multipart) part.getContent();
                }







                if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                    // attachment
                   *//* String fileName = part.getFileName();
                    attachment += fileName + ", ";
                    part.saveFile("" + File.separator + fileName);*//*
                } else {
                    // message
                    body = part.getContent().toString();
                }
            }

            if (attachment.length() > 1) {
                attachment = attachment.substring(0, attachment.length() - 2);
            }
        }*/
        String[] bodyAttachement = new String[2];
        bodyAttachement[0]=body;
        bodyAttachement[1]=attachment;
        return bodyAttachement;
    }


    private void setRecipient(Incoming newMail, MimeMessage message) throws MessagingException {
        Address[] recipients = message.getRecipients(Message.RecipientType.TO);
        String recipientlist = "";
        if (recipients != null){
            StringBuffer recipientString = new StringBuffer();
            for (Address recipient : recipients) {
                recipientString.append("," + recipient.toString());
            }
            recipientlist =  recipientString.toString().substring(1);
        }
        newMail.setRecipient(recipientlist);
    }

    private void setSender(Incoming newMail, MimeMessage message) throws MessagingException {
        Address[] senders = message.getFrom();
        String senderlist= "";
        if(senders != null){
            StringBuffer senderString = new StringBuffer();
            for (Address sender : senders) {
                senderString.append("," + sender.toString());

            }
            senderlist =  senderString.toString().substring(1);

        }
        newMail.setSender(senderlist);
    }


}
