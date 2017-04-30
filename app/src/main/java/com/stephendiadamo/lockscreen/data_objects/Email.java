package com.stephendiadamo.lockscreen.data_objects;

/**
 * Created by stephendiadamo on 2017-04-28.
 */

public class Email {
    public String subject;
    public String from;
    public String cc;
    public String bcc;
    public String textBody;

    public Email(String subject, String from, String bcc, String cc, String textBody) {
        this.subject = subject;
        this.from = from;
        this.bcc = bcc;
        this.cc = cc;
        this.textBody = textBody;
    }
}
