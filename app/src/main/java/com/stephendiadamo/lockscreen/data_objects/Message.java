package com.stephendiadamo.lockscreen.data_objects;

/**
 * Created by stephendiadamo on 2017-04-28.
 */

public class Message {
    public String from;
    public String textBody;

    public Message(String from, String textBody) {
        this.from = from;
        this.textBody = textBody;
    }
}
