package shuai.webmail.entities;

/**
 * Created by ivy on 11/8/14.
 */
public class Incoming {
    public String id;
    public String sender;
    public String recipient;
    public String subject;
    public String body;
    int attached = 0;
    int label = 0;
    public String time;

    public Incoming(){
    }

    public Incoming(String id, String sender, String recipient, String subject, String body) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

    public String getID(){return id;}
    public String getSender(){return sender;}
    public String getRecipient(){return recipient;}
    public String getSubject() { return subject; }
    public String getBody() { return body; }
    public int hasAttachement(){return attached;}
    public int getLabel() { return label; }
    public String getTime() { return time; }

    public void setID(String ID){this.id = ID;}
    public void setSender(String sender){this.sender = sender;}
    public void setRecipient(String recipient){this.recipient = recipient;}
    public void setSubject(String subject) { this.subject =  subject; }
    public void setBody(String body) { this.body =  body; }
    public void setAttachment(int attached) { this.attached =  attached; }
    public void setLabel(int label) { this.label = label; }
    public void setTime(String time) { this.time =  time; }

}
