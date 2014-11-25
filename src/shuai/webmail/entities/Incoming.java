package shuai.webmail.entities;

/**
 * Created by ivy on 11/8/14.
 */
public class Incoming extends Email {

    public Incoming(){
    }

    public Incoming(String id, String sender, String recipient, String subject, String body) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

}
