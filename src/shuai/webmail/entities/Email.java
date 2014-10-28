package shuai.webmail.entities;

public class Email {
    String subject;
    String body;
    String sender;
    String recipient;
    Boolean attached = false;
    public Email(String subject, String body, String sender, String recipient) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.recipient = recipient;
    }
    public String getSubject() { return subject; }
    public String getBody() { return body; }
    public String getSender(){return sender;}
    public String getRecipient(){return recipient;}
    public Boolean hasAttachement(){return attached;}

    public void view(){

    }

    public void delete(){

    }

    public void compose(){

    }
    public void send(){

    }

}
