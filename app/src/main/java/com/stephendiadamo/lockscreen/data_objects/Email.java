package com.stephendiadamo.lockscreen.data_objects;

/**
 * Created by stephendiadamo on 2017-04-28.
 */

public class Email {
    public String subject;
    public String to;
    public String cc;
    public String bcc;
    public String textBody;

    public Email(String subject, String to, String bcc, String cc, String textBody) {
        this.subject = subject;
        this.to = to;
        this.bcc = bcc;
        this.cc = cc;
        this.textBody = textBody;
    }
}
