package shuai.webmail.entities;

public class Email {
    String subject;
    String body;
    String sender;
    String recepient;
    Boolean attached;
    public Email(String subject, String body, String sender, String recepient) {
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.recepient = recepient;
    }
    public String getSubject() { return subject; }
    public String getBody() { return body; }
    public String getSender(){return sender;}
    public String getRecepient(){return recepient;}

    public void view(){

    }

    public void delete(){

    }

    public void compose(){

    }
    public void send(){

    }




}
