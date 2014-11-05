package shuai.webmail.entities;

public class Email {
    int id;
    String sender;
    String recipient;
    String subject;
    String body;
    Boolean attached = false;
    public Email(){}
    public Email( String sender, String recipient, String subject, String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

    public String getSender(){return sender;}
    public String getRecipient(){return recipient;}
    public String getSubject() { return subject; }
    public String getBody() { return body; }
    public Boolean hasAttachement(){return attached;}

    public void setSender(String sender){this.sender = sender;}
    public void setRecipient(String recipient){this.recipient = recipient;}
    public void setSubject(String subject) { this.subject =  subject; }
    public void setBody(String body) { this.body =  body; }



    public void view(){

    }

    public void delete(){

    }

    public void compose(){

    }
    public void send(){

    }

}
