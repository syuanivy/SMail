package shuai.webmail.entities;

public class Outgoing {
    int id=-1;
    String sender;
    String recipient;
    String subject;
    String body;
    String attached = "0";
    String sent = "1";
    String time;
    public Outgoing(){}
    public Outgoing(String sender, String recipient, String subject, String body) {
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
    public String isSent() { return sent; }
    public String getTime() { return time; }

    public void setSender(String sender){this.sender = sender;}
    public void setRecipient(String recipient){this.recipient = recipient;}
    public void setSubject(String subject) { this.subject =  subject; }
    public void setBody(String body) { this.body =  body; }
    public void setAttachment(String attached) { this.attached =  attached; }
    public void setSent(String sent) { this.sent =  sent; }
    public void setTime(String time) { this.time =  time; }


}
