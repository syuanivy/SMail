package shuai.webmail.mail_services;

import shuai.webmail.entities.Account;
import shuai.webmail.entities.Incoming;
import shuai.webmail.managers.EmailManager;
import sun.misc.BASE64Decoder;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
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
        StringBuffer content = new StringBuffer();
        try {
            //obtain the whole message from the server including the end signal
            while (!(msgLine = in.readLine()).equalsIgnoreCase(".")) {
                content.append(msgLine + "\n");
            }
            content.append("." + "\n");

            Session session = Session.getDefaultInstance(new Properties());
            InputStream inputStream = new ByteArrayInputStream(content.toString().getBytes());
            MimeMessage message = new MimeMessage(session, inputStream);
            newMail.setID(UID);
            newMail.setTime(message.getSentDate().toString());
            newMail.setSubject(message.getSubject());

            String senderlist = getSenderList(message);
            String recipientlist = getRecipientList(message);
            newMail.setSender(senderlist);
            newMail.setRecipient(recipientlist);

            String contentType = message.getContentType();
            String body = getBodyAttachment(message, contentType)[0];
            String attachment = getBodyAttachment(message, contentType)[1];

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

    private String[] getBodyAttachment(MimeMessage message, String contentType) throws IOException, MessagingException {
        String body = "";
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
                    body = part.getContent().toString();
                }
            }

            if (attachment.length() > 1) {
                attachment = attachment.substring(0, attachment.length() - 2);
            }
        } else if (contentType.contains("text/plain")
                || contentType.contains("text/html")) {
            Object Body = message.getContent();
            if (Body != null) {
                body = Body.toString();
            }
        }

        String[] bodyAttachement = new String[2];
        bodyAttachement[0]=body;
        bodyAttachement[1]=attachment;
        return bodyAttachement;
    }

    private String getRecipientList(MimeMessage message) throws MessagingException {
        Address[] recipients = message.getRecipients(Message.RecipientType.TO);
        if (recipients==null) return "";
        StringBuffer recipientString = new StringBuffer();
        for (Address recipient : recipients) {
            recipientString.append("," + recipient.toString());
        }
        return recipientString.toString().substring(1);
    }

    private String getSenderList(MimeMessage message) throws MessagingException {
        Address[] senders = message.getFrom();
        if (senders==null) return "";
        StringBuffer senderString = new StringBuffer();
        for (Address sender : senders) {
            senderString.append("," + sender.toString());

        }
        return senderString.toString().substring(1);
    }


}
