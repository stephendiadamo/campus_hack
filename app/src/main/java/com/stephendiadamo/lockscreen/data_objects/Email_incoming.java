package com.stephendiadamo.lockscreen.data_objects;

public class Email_incoming {
    public String subject;
    public String from;
    public String cc;
    public String bcc;
    public String textBody;

    public Email_incoming(String subject, String from, String bcc, String cc, String textBody) {
        this.subject = subject;
        this.from = from;
        this.bcc = bcc;
        this.cc = cc;
        this.textBody = textBody;
    }
}
