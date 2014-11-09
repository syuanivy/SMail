package shuai.webmail.entities;

/**
 * Created by ivy on 11/8/14.
 */
public class Incoming {
    String id;
    String sender;
    String recipient;
    String subject;
    String body;
    String attached = "0";
    String label = "0";
    String time;
    public Incoming(){}
    public Incoming(String sender, String recipient, String subject, String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }


    public String getSender(){return sender;}
    public String getRecipient(){return recipient;}
    public String getSubject() { return subject; }
    public String getBody() { return body; }
    public String hasAttachement(){return attached;}
    public String getLabel() { return label; }
    public String getTime() { return time; }

    public void setSender(String sender){this.sender = sender;}
    public void setRecipient(String recipient){this.recipient = recipient;}
    public void setSubject(String subject) { this.subject =  subject; }
    public void setBody(String body) { this.body =  body; }
    public void setAttachment(String attached) { this.attached =  attached; }
    public void setLabel(String label) { this.label = label; }
    public void setTime(String time) { this.time =  time; }

}
